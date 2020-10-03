package com.opense.traininggain.controller;

import com.opense.traininggain.domain.model.Specialist;
import com.opense.traininggain.domain.service.SpecialistService;
import com.opense.traininggain.resource.SpecialistResource;
import com.opense.traininggain.resource.SaveSpecialistResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
public class SpecialistController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SpecialistService specialistService;


    @Operation(summary = "Get Specialists", description = "Get All Customers by Pages", tags = {"specialists"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Specialists returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/specialists")
    public Page<SpecialistResource> getAllSpecialist(Pageable pageable) {
        Page<Specialist> specialistPage = specialistService.getAllSpecialists(pageable);
        List<SpecialistResource> resources = specialistPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create Specialist", description = "Create a new Specialist", tags = {"specialists"})
    @PostMapping("/specialists")
    public SpecialistResource createSpecialist(@Valid @RequestBody SaveSpecialistResource resource) {
        Specialist specialist = convertToEntity(resource);
        return convertToResource(specialistService.createSpecialist(specialist));
    }

    @Operation(summary = "Update Specialist", description = "Update Specialist for given Id", tags = {"specialists"})
    @PutMapping("/specialist/{cSpecialist}")
    public SpecialistResource updateSpecialist(@PathVariable Long cSpecialist, @RequestBody SaveSpecialistResource resource) {
        Specialist specialist = convertToEntity(resource);
        return convertToResource(specialistService.updateSpecialist(cSpecialist, specialist));
    }

    @Operation(summary = "Delete Specialist", description = "Delete Specialist with given Id", tags = {"specialists"})
    @DeleteMapping("/specialist/{cSpecialist}")
    public ResponseEntity<?> deleteSpecialist(@PathVariable Long cSpecialist) {
        return specialistService.deleteSpecialist(cSpecialist);
    }



    private Specialist convertToEntity(SaveSpecialistResource resource) {
        return mapper.map(resource, Specialist.class);
    }

    private SpecialistResource convertToResource(Specialist entity) {
        return mapper.map(entity, SpecialistResource.class);
    }

}
