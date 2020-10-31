package com.opense.traininggain.domain.service;

import com.opense.traininggain.domain.model.SubscriptionPlan;
import com.opense.traininggain.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SubscriptionPlanService {
    Page<SubscriptionPlan> getAllSubscriptionPlans(Pageable pageable);
    Page<SubscriptionPlan> getAllSubscriptionPlansByCustomerId(Long customerId,Pageable pageable);
    SubscriptionPlan getSubscriptionPlansById(Long subscriptionPlanId);
    SubscriptionPlan createSubscriptionPlans(SubscriptionPlan subscriptionPlan);
    SubscriptionPlan updateSubscriptionPlans(Long subscriptionPlanId, SubscriptionPlan subscriptionPlanDetails);
    ResponseEntity<?> deleteSubscriptionPlans(Long subscriptionPlanId);

}
