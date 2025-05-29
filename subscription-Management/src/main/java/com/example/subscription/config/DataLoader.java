package com.example.subscription.config;

import com.example.subscription.model.Plan;
import com.example.subscription.repository.PlanRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    private final PlanRepository planRepository;

    public DataLoader(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @PostConstruct
    public void loadData() {
        if (planRepository.count() == 0) {
            planRepository.save(new Plan(null, "Basic", 9.99, "Feature1, Feature2", 30));
            planRepository.save(new Plan(null, "Pro", 19.99, "Feature1, Feature2, Feature3", 90));
        }
    }
}