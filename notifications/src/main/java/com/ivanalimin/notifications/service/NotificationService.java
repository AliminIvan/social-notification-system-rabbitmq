package com.ivanalimin.notifications.service;

import com.ivanalimin.dto.NotificationDTO;
import com.ivanalimin.notifications.model.Notification;
import com.ivanalimin.notifications.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository repository;

    @RabbitListener(queues = "notifications_subscribers")
    public void handleNotification(NotificationDTO notificationDTO) {
        log.info("Received notification for user: {} about: {}",
                notificationDTO.getRecipient(),
                notificationDTO.getMessage());

        Notification notification = new Notification();
        notification.setSubscriber(notificationDTO.getRecipient());
        notification.setMessage(notificationDTO.getMessage());
        notification.setCreatedAt(LocalDateTime.now());

        repository.save(notification);

        sendNotificationToUser(notificationDTO);
    }

    private void sendNotificationToUser(NotificationDTO notificationDTO) {
        log.info("Sending personalized notification to user: {}", notificationDTO.getRecipient());
    }
}
