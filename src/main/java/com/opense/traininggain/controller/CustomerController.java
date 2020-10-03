package com.opense.traininggain.controller;

import com.opense.traininggain.domain.model.Customer;
import com.opense.traininggain.domain.service.CustomerService;
import com.opense.traininggain.resource.CustomerResource;
import com.opense.traininggain.resource.SaveCustomerResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public Page<CustomerResource> getAllCustomers(Pageable pageable) {
        Page<Customer> customersPage = customerService.getAllCustomers(pageable);
        List<CustomerResource> resources = customersPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }


    @PostMapping("/customers")
    public CustomerResource createCustomer(@Valid @RequestBody SaveCustomerResource resource) {
        Customer customer = convertToEntity(resource);
        return convertToResource(customerService.createCustomer(customer));
    }


    @PutMapping("/customers/{cCustomer}")
    public CustomerResource updateCustomer(@PathVariable Long cCustomer, @RequestBody SaveCustomerResource resource) {
        Customer customer = convertToEntity(resource);
        return convertToResource(customerService.updateCustomer(cCustomer, customer));
    }


    @DeleteMapping("/customers/{cCustomer}")
    public ResponseEntity<?> deletePost(@PathVariable Long cCustomer) {
        return customerService.deleteCustomer(cCustomer);
    }

    private Customer convertToEntity(SaveCustomerResource resource) {
        return mapper.map(resource, Customer.class);
    }

    private CustomerResource convertToResource(Customer entity) {
        return mapper.map(entity, CustomerResource.class);
    }
}
