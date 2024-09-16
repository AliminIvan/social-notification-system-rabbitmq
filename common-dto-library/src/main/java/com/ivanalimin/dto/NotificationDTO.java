package com.ivanalimin.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class NotificationDTO {

    private String recipient;
    private String message;
    private LocalDateTime sentAt;

    public NotificationDTO() {
    }

    public NotificationDTO(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
        this.sentAt = LocalDateTime.now();
    }

    public NotificationDTO(String message) {
        this.message = message;
        this.sentAt = LocalDateTime.now();
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationDTO that)) return false;
        return Objects.equals(recipient, that.recipient) && Objects.equals(message, that.message) && Objects.equals(sentAt, that.sentAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipient, message, sentAt);
    }

    @Override
    public String toString() {
        return "NotificationDTO{" +
                "recipient='" + recipient + '\'' +
                ", message='" + message + '\'' +
                ", sentAt=" + sentAt +
                '}';
    }
}
