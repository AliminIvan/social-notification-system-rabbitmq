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
    public ResponseEntity<Subscription> subscribe(@RequestBody Subscription subscription) {
        Subscription createdSubscription = subscriptionService.subscribe(subscription);
        return new ResponseEntity<>(createdSubscription, HttpStatus.CREATED);
    }

    @DeleteMapping("/unsubscribe")
    public ResponseEntity<Void> unsubscribe(@RequestBody Subscription subscription) {
        subscriptionService.unsubscribe(subscription);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
