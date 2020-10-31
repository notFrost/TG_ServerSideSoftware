package com.opense.traininggain.controller;

import com.opense.traininggain.domain.model.SubscriptionPlan;
import com.opense.traininggain.domain.service.SubscriptionPlanService;
import com.opense.traininggain.resource.SaveSubscriptionPlanResource;
import com.opense.traininggain.resource.SubscriptionPlanResource;
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
public class SubscriptionPlanController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SubscriptionPlanService subscriptionPlanService;
    @Operation(summary = "Get SubscriptionPlans by CustomerId", description = "Get all SubscriptionsPlans by CustomerId", tags = {"SubscriptionPlans"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All plans returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/customers/{customerId}/plans")
    public Page<SubscriptionPlanResource> getAllSubscriptionPlanByCustomerId(
            @PathVariable(name = "customerId") Long customerId,
            Pageable pageable) {
        List<SubscriptionPlanResource> subscriptionPlans = subscriptionPlanService.getAllSubscriptionPlansByCustomerId(customerId, pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int subscriptionPlansCount = subscriptionPlans.size();
        return new PageImpl<>(subscriptionPlans, pageable, subscriptionPlansCount);
    }

    @Operation(summary = "Get SubscriptionPlans", description = "Get all SubscriptionsPlans", tags = {"SubscriptionPlans"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All plans returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/plans")
    public Page<SubscriptionPlanResource> getAllSubscriptionPlans(Pageable pageable) {
        List<SubscriptionPlanResource> subscriptionPlans = subscriptionPlanService.getAllSubscriptionPlans(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int subscriptionPlansCount = subscriptionPlans.size();
        return new PageImpl<>(subscriptionPlans, pageable, subscriptionPlansCount);

    }
    @Operation(summary = "Get SubscriptionPlans By Id", description = "Get a SubscriptionsPlans by Id", tags = {"SubscriptionPlans"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "plan returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/plans/{id}")
    public SubscriptionPlanResource getSubscriptionPlansById(
            @PathVariable(name = "id") Long subscriptionPlanId) {
        return convertToResource(subscriptionPlanService.getSubscriptionPlansById(subscriptionPlanId));
    }
    @Operation(summary = "Create subscriptionPlan", description = "Create a new subscriptionPlan", tags = {"SubscriptionPlans"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created subscriptionPlan", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/plans")
    public SubscriptionPlanResource createSubscriptionPlan(@Valid @RequestBody SaveSubscriptionPlanResource resource) {
        return convertToResource(subscriptionPlanService.createSubscriptionPlans(
                convertToEntity(resource)));
    }
    @Operation(summary = "Update subscriptionPlan", description = "Update subscriptionPlan for given Id", tags = {"SubscriptionPlans"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " subscriptionPlan Updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/plans/{id}")
    public SubscriptionPlanResource updateSubscriptionPlan(
            @PathVariable(name = "id") Long tagId,
            @Valid @RequestBody SaveSubscriptionPlanResource resource) {
        return convertToResource(subscriptionPlanService.updateSubscriptionPlans(tagId, convertToEntity(resource)));
    }

    private SubscriptionPlan convertToEntity(SaveSubscriptionPlanResource resource) {
        return mapper.map(resource, SubscriptionPlan.class);
    }

    private SubscriptionPlanResource convertToResource(SubscriptionPlan entity) {
        return mapper.map(entity, SubscriptionPlanResource.class);
    }
}
