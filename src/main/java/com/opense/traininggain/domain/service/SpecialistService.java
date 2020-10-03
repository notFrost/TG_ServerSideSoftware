package com.opense.traininggain.domain.service;

import com.opense.traininggain.domain.model.Specialist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SpecialistService {
    Page<Specialist> getAllSpecialists(Pageable pageable);
    Specialist getSpecialistById(Long cSpecialist);
    Specialist createSpecialist(Specialist specialist);
    Specialist updateSpecialist(Long cSpecialist, Specialist specialistDetails);
    ResponseEntity<?> deleteSpecialist(Long cSpecialist);
}