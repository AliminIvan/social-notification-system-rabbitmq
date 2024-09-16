package com.ivanalimin.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class PublicationDTO {

    private UUID id;
    private String author;
    private String content;
    private LocalDateTime createdAt;

    public PublicationDTO() {
    }

    public PublicationDTO(UUID id, String author, String content, LocalDateTime createdAt) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PublicationDTO that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(author, that.author) && Objects.equals(content, that.content) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, content, createdAt);
    }

    @Override
    public String toString() {
        return "PublicationDTO{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
