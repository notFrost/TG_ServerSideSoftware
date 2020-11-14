package com.opense.traininggain.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "subscription_plans")
public class SubscriptionPlan extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Size(max = 25)
    private String name;

    @NotNull
    @Size(max = 50)
    private String description;

    @NotNull
    private int cost;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy="subscriptionPlans")
    private List<Customer> customers;

    public SubscriptionPlan() {
    }

    public SubscriptionPlan(@NotNull @Size(max = 25) String name,@NotNull @Size(max = 50) String description, @NotNull int cost) {
        this.name=name;
        this.description = description;
        this.cost = cost;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public SubscriptionPlan setCustomers(List<Customer> customers) {
        this.customers = customers;
        return this;
    }

    public String getName() {
        return name;
    }

    public SubscriptionPlan setName(String name) {
        this.name = name;
        return this;
    }

}
