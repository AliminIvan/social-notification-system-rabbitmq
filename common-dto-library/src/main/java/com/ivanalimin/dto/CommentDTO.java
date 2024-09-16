package com.ivanalimin.dto;

import java.util.Objects;

public class CommentDTO {

    private String userId;
    private String publicationId;
    private String content;

    public CommentDTO() {
    }

    public CommentDTO(String userId, String publicationId, String content) {
        this.userId = userId;
        this.publicationId = publicationId;
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentDTO that)) return false;
        return Objects.equals(userId, that.userId) && Objects.equals(publicationId, that.publicationId) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, publicationId, content);
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "userId='" + userId + '\'' +
                ", publicationId='" + publicationId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
