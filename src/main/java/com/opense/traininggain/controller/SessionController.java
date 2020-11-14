package com.opense.traininggain.controller;

import com.opense.traininggain.domain.model.Session;
import com.opense.traininggain.domain.service.SessionService;
import com.opense.traininggain.resource.SaveSessionResource;
import com.opense.traininggain.resource.SessionResource;
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
public class SessionController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SessionService sessionService;

    @Operation(summary = "Get Sessions by specialistId", description = "Get All Sessions by specialistId", tags = {"Sessions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Sessions returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/specialists/{specialistId}/sessions")
    public Page<SessionResource> getAllSessionsBySpecialistId(
            @PathVariable Long specialistId, Pageable pageable) {

        Page<Session> sessionPage = sessionService.getAllSessionsBySpecialistId(specialistId, pageable);
        List<SessionResource> resources = sessionPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(tags = {"Sessions"})
    @GetMapping("/sessions")
    public Page<SessionResource> getAllSessions(Pageable pageable) {
        Page<Session> sessionPage = sessionService.getAllSessions(pageable);
        List<SessionResource> resources = sessionPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(tags = {"Sessions"})
    @GetMapping("/sessions/{title}")
    public SessionResource getAllSessions(@PathVariable (value = "title") String title) {
        return convertToResource(sessionService.getSessionsByTitle(title));
    }


    @Operation(summary = "Get Session by Id and specialistId", description = "Get a Session by Id and specialistId", tags = {"Sessions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/specialists/{specialistId}/sessions/{sessionId}")
    public SessionResource getSessionByIdAndSpecialistId(@PathVariable(name = "specialistId") Long specialistId,
                                                   @PathVariable(name = "sessionId") Long sessionId) {
        return convertToResource(sessionService.getSessionByIdAndSpecialistId(specialistId, sessionId));
    }

    @Operation(summary = "Create Session", description = "Create a Session ", tags = {"Sessions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created Session", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/specialists/{specialistId}/sessions")
    public SessionResource createSession(
            @PathVariable Long specialistId,
            @Valid @RequestBody SaveSessionResource resource) {
        return convertToResource(sessionService.createSession(specialistId, convertToEntity(resource)));
    }
    @Operation(summary = "Update Session", description = "Update a Session ", tags = {"Sessions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Session", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/specialists/{specialistId}/sessions/{sessionId}")
    public SessionResource updateSession(
            @PathVariable(value = "specialistId") Long specialistId,
            @PathVariable(value = "sessionId") Long sessionId,
            @Valid @RequestBody SaveSessionResource resource) {
        return convertToResource(sessionService.updateSession(specialistId, sessionId, convertToEntity(resource)));
    }
    @Operation(summary = "Delete Session", description = "Delete a Session ", tags = {"Sessions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted Session", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/specialists/{specialistId}/sessions/{sessionId}")
    public ResponseEntity<?> deleteSession(
            @PathVariable (value = "specialistId") Long specialistId,
            @PathVariable (value = "sessionId") Long sessionId) {
        return sessionService.deleteSession(specialistId, sessionId);
    }

    private Session convertToEntity(SaveSessionResource resource) {
        return mapper.map(resource, Session.class);
    }

    private SessionResource convertToResource(Session entity) {
        return mapper.map(entity, SessionResource.class);
    }
}
