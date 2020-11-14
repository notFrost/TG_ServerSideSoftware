package com.opense.traininggain.domain.service;

import com.opense.traininggain.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    Page<Customer> getAllCustomers(Pageable pageable);
    Customer getCustomerById(Long customerId);
    Customer createCustomer(Long userId,Customer customer);
    Customer updateCustomer(Long customerId, Customer customerDetails);
    ResponseEntity<?> deleteCustomer(Long customerId);
    Customer assignSubscription(Long customerId, Long subscriptionPlanId);
    Customer assignReview(Long customerId,Long SpecialistId);
    Customer unassignSubscription(Long customerId, Long subscriptionPlanId);
    Page<Customer> getAllCustomersBySubscriptionPlanIdId(Long subscriptionPlanIdId, Pageable pageable);
    Page<Customer> getAllCustomersBySpecialistId(Long SpecialistId, Pageable pageable);
}
