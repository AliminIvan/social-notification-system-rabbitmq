package com.ivanalimin.activity.service;

import com.ivanalimin.activity.model.Activity;
import com.ivanalimin.activity.repository.ActivityRepository;
import com.ivanalimin.dto.CommentDTO;
import com.ivanalimin.dto.LikeDTO;
import com.ivanalimin.dto.NotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "new_likes")
    public void handleLike(LikeDTO likeDTO) {
        String message = "User " + likeDTO.getUserId() + " liked your publication.";
        saveActivity(likeDTO.getUserId(), likeDTO.getPublicationId(), message);
        sendNotification(message);
    }

    @RabbitListener(queues = "new_comments")
    public void handleComment(CommentDTO commentDTO) {
        String message = "User " + commentDTO.getUserId() + " commented your publication: " + commentDTO.getContent();
        saveActivity(commentDTO.getUserId(), commentDTO.getPublicationId(), message);
        sendNotification(message);
    }

    private void saveActivity(String userId, String publicationId, String message) {
        Activity activity = new Activity(null, userId, publicationId, message, LocalDateTime.now());
        activityRepository.save(activity);
    }

    private void sendNotification(String message) {
        NotificationDTO notification = new NotificationDTO(message);
        rabbitTemplate.convertAndSend("notifications_activity", notification);
    }
}
