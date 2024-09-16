package com.ivanalimin.subscriptions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "subscriber", nullable = false)
    private String subscriber;

    @Column(name = "author", nullable = false)
    private String author;

    public Subscription(String subscriber, String author) {
        this.subscriber = subscriber;
        this.author = author;
    }
}
