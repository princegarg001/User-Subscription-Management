package com.example.subscription.service;

import com.example.subscription.model.Subscription;
import com.example.subscription.repository.SubscriptionRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class SubscriptionExpiryScheduler {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionExpiryScheduler(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Scheduled(fixedRate = 86400000) // Once a day
    public void expireSubscriptions() {
        List<Subscription> subs = subscriptionRepository.findAll();
        for (Subscription sub : subs) {
            if ("ACTIVE".equals(sub.getStatus()) && LocalDate.now().isAfter(sub.getEndDate())) {
                sub.setStatus("EXPIRED");
                subscriptionRepository.save(sub);
            }
        }
    }
}