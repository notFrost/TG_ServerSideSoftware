package com.opense.traininggain.domain.service;

import com.opense.traininggain.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    Page<Customer> getAllCustomers(Pageable pageable);
    Customer getCustomerById(Long cCustomer);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long cCustomer, Customer customerDetails);
    ResponseEntity<?> deleteCustomer(Long cCustomer);
}
