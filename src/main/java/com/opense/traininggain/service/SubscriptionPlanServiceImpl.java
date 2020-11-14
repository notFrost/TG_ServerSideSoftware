package com.opense.traininggain.service;

import com.opense.traininggain.domain.model.SubscriptionPlan;
import com.opense.traininggain.domain.model.User;
import com.opense.traininggain.domain.repository.CustomerRepository;
import com.opense.traininggain.domain.repository.SubscriptionPlanRepository;
import com.opense.traininggain.domain.service.SubscriptionPlanService;
import com.opense.traininggain.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<SubscriptionPlan> getAllSubscriptionPlans(Pageable pageable) {
       return subscriptionPlanRepository.findAll(pageable);
    }

    @Override
    public Page<SubscriptionPlan> getAllSubscriptionPlansByCustomerId(Long customerId, Pageable pageable) {
        return customerRepository.findById(customerId)
                .map(customer -> {
                    List<SubscriptionPlan> subscriptionPlans = customer.getSubscriptionPlans();
                    int subscriptionsPlansCount = subscriptionPlans.size();
                    return new PageImpl<>(subscriptionPlans, pageable, subscriptionsPlansCount);
                }).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId));
    }

    @Override
    public SubscriptionPlan getSubscriptionPlansById(Long subscriptionPlanId) {
        return subscriptionPlanRepository.findById(subscriptionPlanId)
                .orElseThrow(() -> new ResourceNotFoundException("subscriptionPlan", "Id", subscriptionPlanId));
    }

    @Override
    public SubscriptionPlan createSubscriptionPlans(SubscriptionPlan subscriptionPlan) {
        return subscriptionPlanRepository.save(subscriptionPlan);
    }

    @Override
    public SubscriptionPlan updateSubscriptionPlans(Long subscriptionPlanId, SubscriptionPlan subscriptionPlanDetails) {
        return subscriptionPlanRepository.findById(subscriptionPlanId).map(subscriptionPlan -> {
            subscriptionPlan.setName(subscriptionPlanDetails.getName());
            subscriptionPlan.setDescription(subscriptionPlanDetails.getDescription());
            subscriptionPlan.setCost(subscriptionPlanDetails.getCost());
            return subscriptionPlanRepository.save(subscriptionPlan);
        }).orElseThrow(() -> new ResourceNotFoundException("subscriptionPlan", "Id", subscriptionPlanId));
    }

    @Override
    public ResponseEntity<?> deleteSubscriptionPlans(Long subscriptionPlanId) {
        return subscriptionPlanRepository.findById(subscriptionPlanId).map(subscriptionPlan -> {
            subscriptionPlanRepository.delete(subscriptionPlan);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("subscriptionPlan", "Id", subscriptionPlanId));
    }
}
