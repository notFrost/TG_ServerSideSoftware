package com.opense.traininggain.service;

import com.opense.traininggain.domain.model.Specialist;
import com.opense.traininggain.domain.service.SpecialistService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public class SpecialistServiceImpl implements SpecialistService {
    @Override
    public Page<Specialist> getAllSpecialists(Pageable pageable) {
        return null;
    }

    @Override
    public Specialist getSpecialistById(Long cSpecialist) {
        return null;
    }

    @Override
    public Specialist createPost(Specialist specialist) {
        return null;
    }

    @Override
    public Specialist updatePost(Long postId, Specialist specialist) {
        return null;
    }

    @Override
    public ResponseEntity<?> deletePost(Long postId) {
        return null;
    }
}
