package com.opense.traininggain.resource;

import com.opense.traininggain.domain.model.AuditModel;


public class SubscriptionPlanResource extends AuditModel {
    private Long id;
    private String description;
    private int cost;

    public Long getId() {
        return id;
    }

    public SubscriptionPlanResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SubscriptionPlanResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getCost() {
        return cost;

    }

    public SubscriptionPlanResource setCost(int cost) {
        this.cost = cost;
        return this;
    }
}
