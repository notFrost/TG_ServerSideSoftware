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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ReviewController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CustomerService customerService;


    @PostMapping("/customers/{customerId}/specialists/{specialistId}")
    public CustomerResource assignReview(
            @PathVariable(name = "customerId") Long customerId,
            @PathVariable(name = "specialistId") Long specialistId) {
        return convertToResource(customerService.assignReview(customerId, specialistId));
    }


    @GetMapping("/specialists/{specialistId}/customers")
    public Page<CustomerResource> getAllCustomersBySpecialistId(
            @PathVariable(name = "specialistId") Long specialistId,
            Pageable pageable) {
        Page<Customer> customersPage = customerService.getAllCustomersBySpecialistId(specialistId, pageable);
        List<CustomerResource> resources = customersPage.getContent().stream()
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
