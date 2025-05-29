package com.example.subscription.service;

import com.example.subscription.model.Plan;
import com.example.subscription.model.Subscription;
import com.example.subscription.repository.PlanRepository;
import com.example.subscription.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final PlanRepository planRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, PlanRepository planRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.planRepository = planRepository;
    }

    public Subscription createSubscription(Long userId, Long planId) {
        Plan plan = planRepository.findById(planId).orElseThrow();
        Subscription subscription = new Subscription();
        subscription.setUserId(userId);
        subscription.setPlan(plan);
        subscription.setStatus("ACTIVE");
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusDays(plan.getDurationInDays()));
        return subscriptionRepository.save(subscription);
    }

    public Optional<Subscription> getSubscription(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    public Subscription updateSubscription(Long userId, Long newPlanId) {
        Subscription subscription = subscriptionRepository.findByUserId(userId).orElseThrow();
        Plan newPlan = planRepository.findById(newPlanId).orElseThrow();
        subscription.setPlan(newPlan);
        subscription.setEndDate(LocalDate.now().plusDays(newPlan.getDurationInDays()));
        return subscriptionRepository.save(subscription);
    }

    public void cancelSubscription(Long userId) {
        Subscription subscription = subscriptionRepository.findByUserId(userId).orElseThrow();
        subscription.setStatus("CANCELLED");
        subscriptionRepository.save(subscription);
    }
}