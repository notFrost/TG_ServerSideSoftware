package com.opense.traininggain.resource;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveSubscriptionPlanResource {

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String description;

    @NotNull
    @NotBlank
    private int cost;

    public String getDescription() {
        return description;
    }

    public SaveSubscriptionPlanResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getCost() {
        return cost;
    }

    public SaveSubscriptionPlanResource setCost(int cost) {
        this.cost = cost;
        return this;
    }
}
