package com.opense.traininggain.domain.repository;

import com.opense.traininggain.domain.model.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
    public Optional<Specialist> findByTitle(String title);
}
