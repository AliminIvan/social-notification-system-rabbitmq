package com.ivanalimin.publications.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Publication {

    private UUID id;
    private String content;
    private String author;
    private LocalDateTime createdAt;

    public Publication(String content, String author) {
        this.id = UUID.randomUUID();
        this.content = content;
        this.author = author;
        this.createdAt = LocalDateTime.now();
    }
}
