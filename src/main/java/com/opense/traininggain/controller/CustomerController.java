package com.opense.traininggain.controller;

import com.opense.traininggain.domain.model.Customer;
import com.opense.traininggain.domain.service.CustomerService;
import com.opense.traininggain.resource.CustomerResource;
import com.opense.traininggain.resource.SaveCustomerResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get Customers", description = "Get All Customers by Pages", tags = {"Customers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Customers returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/customers")
    public Page<CustomerResource> getAllCustomers(Pageable pageable) {
        Page<Customer> customersPage = customerService.getAllCustomers(pageable);
        List<CustomerResource> resources = customersPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create Customer", description = "Create a new Customer", tags = {"Customers"})
    @PostMapping("/users/{usersId}/customer")
    public CustomerResource createCustomer(
            @PathVariable Long usersId,
            @Valid @RequestBody SaveCustomerResource resource) {
        Customer customer = convertToEntity(resource);
        return convertToResource(customerService.createCustomer(usersId,customer));
    }

    @Operation(summary = "Update Customer", description = "Update Customer for given Id", tags = {"Customers"})
    @PutMapping("/customers/{customerId}")
    public CustomerResource updateCustomer(@PathVariable Long customerId, @RequestBody SaveCustomerResource resource) {
        Customer customer = convertToEntity(resource);
        return convertToResource(customerService.updateCustomer(customerId, customer));
    }

    @Operation(summary = "Delete Customer", description = "Delete Customer with given Id", tags = {"Customers"})
    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<?> deletePost(@PathVariable Long customerId) {
        return customerService.deleteCustomer(customerId);
    }

    private Customer convertToEntity(SaveCustomerResource resource) {
        return mapper.map(resource, Customer.class);
    }

    private CustomerResource convertToResource(Customer entity) {
        return mapper.map(entity, CustomerResource.class);
    }
}
