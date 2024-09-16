package com.ivanalimin.dto;

import java.util.Objects;

public class LikeDTO {

    private String userId;
    private String publicationId;

    public LikeDTO() {
    }

    public LikeDTO(String userId, String publicationId) {
        this.userId = userId;
        this.publicationId = publicationId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LikeDTO likeDTO)) return false;
        return Objects.equals(userId, likeDTO.userId) && Objects.equals(publicationId, likeDTO.publicationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, publicationId);
    }

    @Override
    public String toString() {
        return "LikeDTO{" +
                "userId='" + userId + '\'' +
                ", publicationId='" + publicationId + '\'' +
                '}';
    }
}
