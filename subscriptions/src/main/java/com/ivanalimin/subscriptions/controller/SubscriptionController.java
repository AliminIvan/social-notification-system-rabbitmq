package com.ivanalimin.subscriptions.controller;

import com.ivanalimin.subscriptions.model.Subscription;
import com.ivanalimin.subscriptions.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/subscribe")
    public ResponseEntity<Subscription> subscribe(@RequestParam String subscriber, @RequestParam String author) {
        Subscription subscription = subscriptionService.subscribe(subscriber, author);
        return new ResponseEntity<>(subscription, HttpStatus.CREATED);
    }

    @DeleteMapping("/unsubscribe")
    public ResponseEntity<Void> unsubscribe(@RequestParam String subscriber, @RequestParam String author) {
        subscriptionService.unsubscribe(subscriber, author);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
