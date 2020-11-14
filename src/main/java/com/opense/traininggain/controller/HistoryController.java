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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class HistoryController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers/{customerId}/sessions/{sessionId}")
    public CustomerResource assignReview(
            @PathVariable(name = "customerId") Long customerId,
            @PathVariable(name = "sessionId") Long sessionId) {
        return convertToResource(customerService.assingHistory(customerId, sessionId));
    }


    @GetMapping("/sessions/{sessionId}/customers")
    public Page<CustomerResource> getAllCustomersBySessionId(
            @PathVariable(name = "sessionId") Long sessionId,
            Pageable pageable) {
        Page<Customer> customersPage = customerService.getAllCustomersBySessionId(sessionId, pageable);
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
