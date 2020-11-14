package com.opense.traininggain.controller;

import com.opense.traininggain.domain.model.Customer;
import com.opense.traininggain.domain.model.Equipament;
import com.opense.traininggain.domain.model.Session;
import com.opense.traininggain.domain.service.EquipamentService;
import com.opense.traininggain.domain.service.SessionService;
import com.opense.traininggain.resource.*;
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
public class EquipamentSessionController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private EquipamentService equipamentService;

    @PostMapping("/sessions/{sessionId}/equipaments/{equipamentId}")
    public SessionResource assignSubscribe(
            @PathVariable(name = "sessionId") Long sessionId,
            @PathVariable(name = "equipamentId") Long equipamentId) {
        return convertToResource(sessionService.assingEquipament(sessionId, equipamentId));
    }

    @DeleteMapping("/sessions/{sessionId}/equipaments/{equipamentId}")
    public SessionResource unAssignSubscribe(
            @PathVariable(name = "sessionId") Long sessionId,
            @PathVariable(name = "equipamentId") Long equipamentId) {
        return convertToResource(sessionService.unassignEquipament(sessionId, equipamentId));
    }

    @GetMapping("/sessions/{sessionId}/equipaments")
    public Page<EquipamentResource> getAllEquipamentsBySessionId(
            @PathVariable(name = "sessionId") Long sessionId,
            Pageable pageable){
        Page<Equipament> equipamentsPage = equipamentService.getAllEquipamentsBySessionId(sessionId, pageable);
        List<EquipamentResource> resources = equipamentsPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    private Session convertToEntity(SaveSessionResource resource) {
        return mapper.map(resource, Session.class);
    }

    private SessionResource convertToResource(Session entity) {
        return mapper.map(entity, SessionResource.class);
    }

    private Equipament convertToEntity(SaveEquipamentResource resource) {
        return mapper.map(resource, Equipament.class);
    }

    private EquipamentResource convertToResource(Equipament entity) {
        return mapper.map(entity, EquipamentResource.class);
    }
}
