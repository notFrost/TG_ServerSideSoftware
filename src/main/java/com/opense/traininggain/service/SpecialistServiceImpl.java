package com.opense.traininggain.service;

import com.opense.traininggain.domain.model.Specialist;
import com.opense.traininggain.domain.repository.SpecialistRepository;
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
    private SpecialistRepository specialistRepository;

    @Override
    public Page<Specialist> getAllSpecialists(Pageable pageable) {
        return specialistRepository.findAll(pageable);
    }

    @Override
    public Specialist getSpecialistById(Long cSpecialist) {
        return specialistRepository.findById(cSpecialist).orElseThrow(()->new ResourceNotFoundException("Specialist", "Id", cSpecialist));
    }

    @Override
    public Specialist createSpecialist(Specialist specialist) {
        return specialistRepository.save(specialist);
    }

    @Override
    public Specialist updateSpecialist(Long cSpecialist, Specialist specialistDetails) {
        Specialist specialist = specialistRepository.findById(cSpecialist)
                .orElseThrow(() -> new ResourceNotFoundException("Specilaist", "Id", cSpecialist));
        specialist.setnSpecialist(specialistDetails.getnSpecialist());
        specialist.setdBirthdate(specialistDetails.getdBirthdate());
        specialist.setfMale(specialistDetails.getfMale());
        specialist.setnPhone(specialistDetails.getnPhone());
        specialist.settEmail(specialistDetails.gettEmail());
        specialist.settAddress(specialistDetails.gettAddress());
        specialist.settDescription(specialistDetails.gettDescription());
        return specialistRepository.save(specialist);
    }

    @Override
    public ResponseEntity<?> deleteSpecialist(Long cSpecialist) {
        Specialist specialist = specialistRepository.findById(cSpecialist).
                orElseThrow(()-> new ResourceNotFoundException("Specialist", "Id", cSpecialist));
        specialistRepository.delete(specialist);
        return ResponseEntity.ok().build();
    }
}
