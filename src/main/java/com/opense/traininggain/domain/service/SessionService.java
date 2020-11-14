package com.opense.traininggain.domain.service;

import com.opense.traininggain.domain.model.Customer;
import com.opense.traininggain.domain.model.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SessionService {
    Page<Session> getAllSessions(Pageable pageable);
    Page<Session> getAllSessionsBySpecialistId(Long specialistId, Pageable pageable);
    Session getSessionByIdAndSpecialistId(Long specialistId, Long sessionId);
    Session createSession(Long specialistId, Session session);
    Session updateSession(Long specialistId, Long sessionId, Session sessionDetails);
    ResponseEntity<?> deleteSession(Long specialistId, Long sessionId);
    Session getSessionsByTitle(String title);
    Session assingEquipament(Long sessionId, Long equipamentId);
    Session unassignEquipament(Long sessionId, Long equipamentId);
    Session assignTagSession(Long sessionId, Long tagId);
    Page<Session> getAllSessionsByTagId(Long tagId, Pageable pageable);

}
