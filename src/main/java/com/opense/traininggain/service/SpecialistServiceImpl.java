package com.opense.traininggain.service;

import com.opense.traininggain.domain.model.Specialist;
import com.opense.traininggain.domain.repository.SpecialistRepository;
import com.opense.traininggain.domain.repository.UserRepository;
import com.opense.traininggain.domain.service.SpecialistService;
import com.opense.traininggain.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SpecialistServiceImpl implements SpecialistService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SpecialistRepository specialistRepository;

    @Override
    public Page<Specialist> getAllSpecialists(Pageable pageable) {
        return specialistRepository.findAll(pageable);
    }

    @Override
    public Specialist getSpecialistById(Long specialistId) {
        return specialistRepository.findById(specialistId).orElseThrow(()->new ResourceNotFoundException("Specialist", "Id", specialistId));
    }
    @Override
    public Specialist createSpecialist(Long userId,Specialist specialist) {
        return userRepository.findById(userId).map(user -> {
            specialist.setUser(user);
            return specialistRepository.save(specialist);
        }).orElseThrow(()->new ResourceNotFoundException(
                "User","Id",userId));


    }

    @Override
    public Specialist updateSpecialist(Long specialistId, Specialist specialistDetails) {
        Specialist specialist = specialistRepository.findById(specialistId)
                .orElseThrow(() -> new ResourceNotFoundException("Specialist", "Id", specialistId));
        specialist.setSpecialty(specialistDetails.getSpecialty());
        return specialistRepository.save(specialist);
    }

    @Override
    public ResponseEntity<?> deleteSpecialist(Long specialistId) {
        Specialist specialist = specialistRepository.findById(specialistId).
                orElseThrow(()-> new ResourceNotFoundException("Specialist", "Id", specialistId));
        specialistRepository.delete(specialist);
        return ResponseEntity.ok().build();
    }
}
