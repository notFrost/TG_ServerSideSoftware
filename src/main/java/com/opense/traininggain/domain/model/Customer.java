package com.opense.traininggain.domain.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="customers")
public class Customer extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "histories",
            joinColumns = { @JoinColumn(name = "customer_id")},
            inverseJoinColumns = { @JoinColumn(name = "session_id")})
    private List<Session> sessions;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "reviews",
            joinColumns = { @JoinColumn(name = "customer_id")},
            inverseJoinColumns = { @JoinColumn(name = "specialist_id")})
    private List<Specialist> specialists;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "subscriptions",
            joinColumns = { @JoinColumn(name = "customer_id")},
            inverseJoinColumns = { @JoinColumn(name = "subscription_plan_id")})
    private List<SubscriptionPlan> subscriptionPlans;

    public Customer() {
    }

    public Customer(@NotNull @Size(max = 30) String description) {
        this.description = description;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Specialist> getSpecialists() {
        return specialists;
    }

    public Customer setSpecialists(List<Specialist> specialists) {
        this.specialists = specialists;
        return this;
    }

    public boolean isReviewedWith(Specialist specialist){
        return this.getSpecialists().contains(specialist);
    }

    public Customer ReviewedWith(Specialist specialist){
        if (!this.isReviewedWith(specialist))
            this.getSpecialists().add(specialist);
        return this;

    }

    public boolean isRecordedWith(Session session){return this.getSessions().contains(session);}

    public Customer RecordedWith(Session session){
        if (!this.isRecordedWith(session))
            this.getSessions().add(session);
        return this;
    }

    public boolean isSubscribeWith(SubscriptionPlan subscriptionPlan) {
        return this.getSubscriptionPlans().contains(subscriptionPlan);
    }

    public Customer SubscribeWith(SubscriptionPlan subscriptionPlan) {
        if(!this.isSubscribeWith(subscriptionPlan))
            this.getSubscriptionPlans().add(subscriptionPlan);
        return this;
    }

    public Customer UnsubscribeWith(SubscriptionPlan SubscriptionPlan) {
        if(this.isSubscribeWith(SubscriptionPlan))
            this.getSubscriptionPlans().remove(SubscriptionPlan);
        return this;
    }

    public List<SubscriptionPlan> getSubscriptionPlans() {
        return subscriptionPlans;
    }

    public Customer setSubscriptionPlans(List<SubscriptionPlan> subscriptionPlans) {
        this.subscriptionPlans = subscriptionPlans;
        return this;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public Customer setSessions(List<Session> sessions) {
        this.sessions = sessions;
        return this;
    }

}
