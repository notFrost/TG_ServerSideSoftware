package com.opense.traininggain.service;

import com.opense.traininggain.domain.model.Session;
import com.opense.traininggain.domain.model.Specialist;
import com.opense.traininggain.domain.repository.SessionRepository;
import com.opense.traininggain.domain.repository.SpecialistRepository;
import com.opense.traininggain.domain.service.SessionService;
import com.opense.traininggain.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SpecialistRepository specialistRepository;
    @Autowired
    private SessionRepository sessionRepository;

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
            session.setTittle(sessionDetails.getTittle());
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
}
