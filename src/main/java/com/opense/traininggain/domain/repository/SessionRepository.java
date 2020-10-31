package com.opense.traininggain.domain.repository;

import com.opense.traininggain.domain.model.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session,Long> {
    Page<Session> findBySpecialistId(Long SpecialistId, Pageable pageable);
    Optional<Session> findByIdAndSpecialistId(Long id, Long SpecialistId);
}
