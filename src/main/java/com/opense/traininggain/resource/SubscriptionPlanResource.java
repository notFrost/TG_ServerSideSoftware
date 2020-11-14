package com.opense.traininggain.resource;

import com.opense.traininggain.domain.model.AuditModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class SubscriptionPlanResource extends AuditModel {
    private Long id;

    private String name;

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

    public String getName() {
        return name;
    }

    public SubscriptionPlanResource setName(String name) {
        this.name = name;
        return this;
    }


    public SubscriptionPlanResource setCost(int cost) {
        this.cost = cost;
        return this;
    }
}
