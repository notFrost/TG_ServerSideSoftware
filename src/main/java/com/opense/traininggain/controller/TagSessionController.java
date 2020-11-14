package com.opense.traininggain.controller;

import com.opense.traininggain.domain.model.Customer;
import com.opense.traininggain.domain.model.Equipament;
import com.opense.traininggain.domain.model.Session;
import com.opense.traininggain.domain.model.Tag;
import com.opense.traininggain.domain.service.EquipamentService;
import com.opense.traininggain.domain.service.SessionService;
import com.opense.traininggain.domain.service.TagService;
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
public class TagSessionController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SessionService sessionService;

    @PostMapping("/sessions/{sessionId}/tags/{tagId}")
    public SessionResource assignReview(
            @PathVariable(name = "sessionId") Long sessionId,
            @PathVariable(name = "tagId") Long tagId) {
        return convertToResource(sessionService.assignTagSession(sessionId, tagId));
    }


    @GetMapping("/tags/{tagId}/sessions")
    public Page<SessionResource> getAllSessionsByTagId(
            @PathVariable(name = "tagId") Long tagId,
            Pageable pageable) {
        Page<Session> sessionPage = sessionService.getAllSessionsByTagId(tagId, pageable);
        List<SessionResource> resources = sessionPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    private Session convertToEntity(SaveSessionResource resource) {
        return mapper.map(resource, Session.class);
    }

    private SessionResource convertToResource(Session entity) {
        return mapper.map(entity, SessionResource.class);
    }

    private Tag convertToEntity(SaveTagResource resource) {
        return mapper.map(resource, Tag.class);
    }

    private TagResource convertToResource(Tag entity) {
        return mapper.map(entity, TagResource.class);
    }
}
