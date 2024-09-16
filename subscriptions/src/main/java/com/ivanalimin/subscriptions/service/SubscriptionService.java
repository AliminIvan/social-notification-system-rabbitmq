package com.ivanalimin.subscriptions.service;

import com.ivanalimin.dto.NotificationDTO;
import com.ivanalimin.dto.PublicationDTO;
import com.ivanalimin.subscriptions.model.Subscription;
import com.ivanalimin.subscriptions.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository repository;
    private final RabbitTemplate rabbitTemplate;

    public Subscription subscribe(Subscription subscription) {
        return repository.save(subscription);
    }

    public void unsubscribe(Subscription subscription) {
        List<Subscription> subscriptions = repository.findAllByAuthor(subscription.getAuthor());
        subscriptions.stream()
                .filter(sub -> sub.getSubscriber().equals(subscription.getSubscriber()))
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
        NotificationDTO notificationDTO = new NotificationDTO(subscriber, message);
        log.info("Sending message to subscriber: {} message: {}", subscriber, message);
        rabbitTemplate.convertAndSend("notifications_subscribers", notificationDTO);
    }
}
