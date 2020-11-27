package com.opense.traininggain.controller;

import com.opense.traininggain.domain.model.Customer;
import com.opense.traininggain.domain.service.CustomerService;
import com.opense.traininggain.resource.CustomerResource;
import com.opense.traininggain.resource.SaveCustomerResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @GetMapping("plans/{subscriptionPlanId}/customers")
    public Page<CustomerResource> getAllCustomersBySubscriptionPlanId(
            @PathVariable(name = "subscriptionPlanId") Long subscriptionPlanId,
            Pageable pageable) {
        Page<Customer> postsPage = customerService.getAllCustomersBySpecialistId(subscriptionPlanId, pageable);
        List<CustomerResource> resources = postsPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private Customer convertToEntity(SaveCustomerResource resource) {
        return mapper.map(resource, Customer.class);
    }

    private CustomerResource convertToResource(Customer entity) {
        return mapper.map(entity, CustomerResource.class);
    }
}