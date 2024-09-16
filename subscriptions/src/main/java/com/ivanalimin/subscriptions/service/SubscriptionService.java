package com.ivanalimin.subscriptions.service;

import com.ivanalimin.dto.PublicationDTO;
import com.ivanalimin.subscriptions.model.Subscription;
import com.ivanalimin.subscriptions.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository repository;

    public Subscription subscribe(String subscriber, String author) {
        Subscription subscription = new Subscription(subscriber, author);
        return repository.save(subscription);
    }

    public void unsubscribe(String subscriber, String author) {
        List<Subscription> subscriptions = repository.findAllByAuthor(author);
        subscriptions.stream()
                .filter(subscription -> subscription.getSubscriber().equals(subscriber))
                .findFirst()
                .ifPresent(repository::delete);
    }

    @RabbitListener(queues = "new_publications")
    public void handleNewPublication(PublicationDTO publicationDTO) {
        List<Subscription> subscribers = repository.findAllByAuthor(publicationDTO.getAuthor());
        subscribers.forEach(subscription -> {
            sendNotification(subscription.getSubscriber(), "New publication from " +
                    publicationDTO.getAuthor() + ": " + publicationDTO.getContent());
        });
    }

    private void sendNotification(String subscriber, String message) {
        log.info("Sending message to subscriber: {} message: {}", subscriber, message);
    }
}
