package com.opense.traininggain.controller;

import com.opense.traininggain.domain.model.Customer;
import com.opense.traininggain.domain.service.CustomerService;
import com.opense.traininggain.resource.CustomerResource;
import com.opense.traininggain.resource.SaveCustomerResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubscriptionsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers/{customerId}/plans/{subscriptionPlanId}")
    public CustomerResource assignSubscribe(
            @PathVariable(name = "customerId") Long customerId,
            @PathVariable(name = "subscriptionPlanId") Long subscriptionPlanId) {
        return convertToResource(customerService.assignSubscription(customerId, subscriptionPlanId));
    }

    @DeleteMapping("/customers/{customerId}/plans/{subscriptionPlanId}")
    public CustomerResource unAssignSubscribe(
            @PathVariable(name = "customerId") Long customerId,
            @PathVariable(name = "subscriptionPlanId") Long subscriptionPlanId) {
        return convertToResource(customerService.unassignSubscription(customerId, subscriptionPlanId));
    }

    private Customer convertToEntity(SaveCustomerResource resource) {
        return mapper.map(resource, Customer.class);
    }

    private CustomerResource convertToResource(Customer entity) {
        return mapper.map(entity, CustomerResource.class);
    }
}