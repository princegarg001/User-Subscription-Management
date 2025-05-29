package com.example.subscription.controller;

import com.example.subscription.model.Subscription;
import com.example.subscription.service.SubscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public Subscription createSubscription(@RequestParam Long userId, @RequestParam Long planId) {
        return subscriptionService.createSubscription(userId, planId);
    }

    @GetMapping("/{userId}")
    public Optional<Subscription> getSubscription(@PathVariable Long userId) {
        return subscriptionService.getSubscription(userId);
    }

    @PutMapping("/{userId}")
    public Subscription updateSubscription(@PathVariable Long userId, @RequestParam Long newPlanId) {
        return subscriptionService.updateSubscription(userId, newPlanId);
    }

    @DeleteMapping("/{userId}")
    public void cancelSubscription(@PathVariable Long userId) {
        subscriptionService.cancelSubscription(userId);
    }
}