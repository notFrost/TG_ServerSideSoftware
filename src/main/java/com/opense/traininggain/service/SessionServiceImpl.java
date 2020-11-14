package com.opense.traininggain.service;

import com.opense.traininggain.domain.model.*;
import com.opense.traininggain.domain.repository.*;
import com.opense.traininggain.domain.service.SessionService;
import com.opense.traininggain.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private SpecialistRepository specialistRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private EquipamentRepository equipamentRepository;

    @Override
    public Page<Session> getAllSessions(Pageable pageable) {
        return sessionRepository.findAll(pageable);
    }

    @Override
    public Page<Session> getAllSessionsBySpecialistId(Long specialistId, Pageable pageable) {
        return sessionRepository.findBySpecialistId(specialistId,pageable);
    }

    @Override
    public Session getSessionByIdAndSpecialistId(Long specialistId, Long sessionId) {
        return sessionRepository.findByIdAndSpecialistId(sessionId,specialistId).orElseThrow(
                ()->new ResourceNotFoundException(
                        "Session not found with Id "+sessionId+" and SpecialistId "+specialistId
                )
        );
    }

    @Override
    public Session createSession(Long specialistId, Session session) {
        return specialistRepository.findById(specialistId).map(specialist -> {
            session.setSpecialist(specialist);
            return sessionRepository.save(session);
        }).orElseThrow(()->new ResourceNotFoundException(
                "Specialist","Id",specialistId
        ));
    }

    @Override
    public Session updateSession(Long specialistId, Long sessionId, Session sessionDetails) {
        if(!specialistRepository.existsById(specialistId))
            throw new ResourceNotFoundException("Specialist","Id",specialistId);
        return sessionRepository.findById(sessionId).map(session -> {
            session.setTitle(sessionDetails.getTitle());
            session.setDescription(sessionDetails.getDescription());
            session.setStartDate(sessionDetails.getStartDate());
            session.setStartHour(sessionDetails.getStartHour());
            session.setEndHour(sessionDetails.getEndHour());
            return sessionRepository.save(session);
        }).orElseThrow(()->new ResourceNotFoundException("Session","Id",sessionId));
    }

    @Override
    public ResponseEntity<?> deleteSession(Long specialistId, Long sessionId) {
        if(!specialistRepository.existsById(specialistId))
            throw new ResourceNotFoundException("Specialist", "Id", specialistId);

        return sessionRepository.findById(sessionId).map(session -> {
            sessionRepository.delete(session);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Session", "Id", sessionId));

    }

    @Override
    public Session getSessionsByTitle(String title) {
        return sessionRepository.findByTitle(title).orElseThrow(()->new ResourceNotFoundException("Session","Title",title));
    }

    @Override
    public Session assingEquipament(Long sessionId, Long equipamentId) {
        Equipament equipament = equipamentRepository.findById(equipamentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Equipament", "Id", equipamentId));
        return sessionRepository.findById(sessionId).map(
                session -> sessionRepository.save(session.AssignWith(equipament)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Session", "Id", sessionId));
    }

    @Override
    public Session unassignEquipament(Long sessionId, Long equipamentId) {
        Equipament equipament = equipamentRepository.findById(equipamentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Equipament", "Id", equipamentId));
        return sessionRepository.findById(sessionId).map(
                session -> sessionRepository.save(session.UnassignWith(equipament)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Session", "Id", sessionId));
    }

    @Override
    public Session assignTagSession(Long sessionId, Long tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tag", "Id", tagId));
        return sessionRepository.findById(sessionId).map(
                session -> sessionRepository.save(session.tagWith(tag)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Session", "Id", sessionId));
    }

    @Override
    public Page<Session> getAllSessionsByTagId(Long tagId, Pageable pageable) {
        return tagRepository.findById(tagId).map(tag -> {
                    List<Session> sessions = tag.getSessions();
                    int sessionCount = sessions.size();
                    return new PageImpl<>(sessions, pageable, sessionCount);
                }
        ).orElseThrow(() -> new ResourceNotFoundException(
                "Tag", "Id", tagId));
    }



}
