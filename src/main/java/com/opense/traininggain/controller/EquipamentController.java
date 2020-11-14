package com.opense.traininggain.controller;

import com.opense.traininggain.domain.model.Customer;
import com.opense.traininggain.domain.model.Equipament;
import com.opense.traininggain.domain.model.User;
import com.opense.traininggain.domain.service.EquipamentService;
import com.opense.traininggain.resource.*;
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
public class EquipamentController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private EquipamentService equipamentService;


    @GetMapping("/equipaments")
    public Page<EquipamentResource> getAllEquipaments(Pageable pageable) {
        Page<Equipament> equipamentPage = equipamentService.getAllEquipaments(pageable);
        List<EquipamentResource> resources = equipamentPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }


    @PostMapping("/equipaments")
    public EquipamentResource createEquipament(
            @Valid @RequestBody SaveEquipamentResource resource) {
        Equipament equipament = convertToEntity(resource);
        return convertToResource(equipamentService.createEquipament(equipament));
    }


    @PutMapping("/equipaments/{equipamentId}")
    public EquipamentResource updateEquipament(@PathVariable Long equipamentId, @RequestBody SaveEquipamentResource resource) {
        Equipament equipament = convertToEntity(resource);
        return convertToResource(equipamentService.updateEquipament(equipamentId,equipament));
    }


    @DeleteMapping("/equipaments/{equipamentId}")
    public ResponseEntity<?> deleteEquipament(@PathVariable Long equipamentId) {
        return equipamentService.deleteEquipament(equipamentId);
    }

    private Equipament convertToEntity(SaveEquipamentResource resource) {
        return mapper.map(resource, Equipament.class);
    }

    private EquipamentResource convertToResource(Equipament entity) {
        return mapper.map(entity, EquipamentResource.class);
    }
}
