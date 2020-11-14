package com.opense.traininggain.service;

import com.opense.traininggain.domain.model.Customer;
import com.opense.traininggain.domain.model.Specialist;
import com.opense.traininggain.domain.model.SubscriptionPlan;
import com.opense.traininggain.domain.repository.CustomerRepository;
import com.opense.traininggain.domain.repository.SpecialistRepository;
import com.opense.traininggain.domain.repository.SubscriptionPlanRepository;
import com.opense.traininggain.domain.repository.UserRepository;
import com.opense.traininggain.domain.service.CustomerService;
import com.opense.traininggain.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;
    @Autowired
    private SpecialistRepository specialistRepository;

    @Override
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer","Id",customerId));
    }

    @Override
    public Customer createCustomer(Long userId,Customer customer) {

        return userRepository.findById(userId).map(user -> {
            customer.setUser(user);
            return customerRepository.save(customer);
        }).orElseThrow(()->new ResourceNotFoundException(
                "User","Id",userId));


    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customerDetails) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer", "Id", customerId));
        customer.setDescription(customerDetails.getDescription());
        return customerRepository.save(customer);
    }

    @Override
    public ResponseEntity<?> deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer", "Id", customerId));
        customerRepository.delete(customer);
        return ResponseEntity.ok().build();
    }

    @Override
    public Customer assignSubscription(Long customerId, Long subscriptionPlanId) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(subscriptionPlanId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "SubscriptionPlan", "Id", subscriptionPlanId));
        return customerRepository.findById(customerId).map(
                customer -> customerRepository.save(customer.SubscribeWith(subscriptionPlan)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer", "Id", customerId));
    }

    @Override
    public Customer assignReview(Long customerId, Long SpecialistId) {
        Specialist specialist=specialistRepository.findById(SpecialistId).orElseThrow(()->new ResourceNotFoundException(
                "Specialist","Id",SpecialistId));
        return customerRepository.findById(customerId).map(
                customer -> customerRepository.save(customer.ReviewedWith(specialist)))
                .orElseThrow(()->new ResourceNotFoundException(
                        "Customer","Id",customerId));
    }

    @Override
    public Customer unassignSubscription(Long customerId, Long subscriptionPlanId) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(subscriptionPlanId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "SubscriptionPlan", "Id", subscriptionPlanId));
        return customerRepository.findById(customerId).map(
                customer -> customerRepository.save(customer.UnsubscribeWith(subscriptionPlan)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer", "Id", customerId));
    }

    @Override
    public Page<Customer> getAllCustomersBySubscriptionPlanIdId(Long subscriptionPlanIdId, Pageable pageable) {
        return subscriptionPlanRepository.findById(subscriptionPlanIdId).map(subscriptionPlan -> {
                    List<Customer> customers = subscriptionPlan.getCustomers();
                    int customersCount = customers.size();
                    return new PageImpl<>(customers, pageable, customersCount);
                }
        ).orElseThrow(() -> new ResourceNotFoundException(
                "SubscriptionPlan", "Id", subscriptionPlanIdId));
    }

    @Override
    public Page<Customer> getAllCustomersBySpecialistId(Long SpecialistId , Pageable pageable) {
        return specialistRepository.findById(SpecialistId).map(specialist -> {
                    List<Customer> customers = specialist.getCustomers();
                    int customersCount = customers.size();
                    return new PageImpl<>(customers, pageable, customersCount);
                }
        ).orElseThrow(() -> new ResourceNotFoundException(
                "Specialist", "Id", SpecialistId));
    }
}
