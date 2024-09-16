package com.ivanalimin.publications.service;

import com.ivanalimin.dto.PublicationDTO;
import com.ivanalimin.publications.model.Publication;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PublicationService {

    private final RabbitTemplate rabbitTemplate;

    public void send(Publication publication) {
        if (publication.getId() == null) {
            publication.setId(UUID.randomUUID());
        }
        if (publication.getCreatedAt() == null) {
            publication.setCreatedAt(LocalDateTime.now());
        }
        PublicationDTO publicationDTO = new PublicationDTO();
        publicationDTO.setId(publication.getId());
        publicationDTO.setContent(publication.getContent());
        publicationDTO.setAuthor(publication.getAuthor());
        publicationDTO.setCreatedAt(publication.getCreatedAt());
        rabbitTemplate.convertAndSend("new_publications", publicationDTO);
    }
}
