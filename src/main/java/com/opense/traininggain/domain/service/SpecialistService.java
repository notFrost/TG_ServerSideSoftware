package com.opense.traininggain.domain.service;

import com.opense.traininggain.domain.model.Specialist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SpecialistService {
    Page<Specialist> getAllSpecialists(Pageable pageable);
    Specialist getSpecialistById(Long cSpecialist);
    Specialist createPost(Specialist specialist);
    Specialist updatePost(Long postId, Specialist specialist);
    ResponseEntity<?> deletePost(Long postId);
}