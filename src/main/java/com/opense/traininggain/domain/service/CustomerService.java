package com.opense.traininggain.domain.service;

import com.opense.traininggain.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    Page<Customer> getAllPosts(Pageable pageable);
    Customer getPostById(Long cCustomer);
    Customer createPost(Customer customer);
    Customer updatePost(Long cCustomer, Customer customer);
    ResponseEntity<?> deletePost(Long cCustomer);
}
