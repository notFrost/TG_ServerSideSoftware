package com.opense.traininggain.service;

import com.opense.traininggain.domain.model.Customer;
import com.opense.traininggain.domain.model.Equipament;
import com.opense.traininggain.domain.repository.EquipamentRepository;
import com.opense.traininggain.domain.repository.SessionRepository;
import com.opense.traininggain.domain.service.EquipamentService;
import com.opense.traininggain.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentServiceImpl implements EquipamentService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private EquipamentRepository equipamentRepository;

    @Override
    public Page<Equipament> getAllEquipaments(Pageable pageable) {
        return equipamentRepository.findAll(pageable);
    }

    @Override
    public Equipament getEquipamentById(Long equipamentId) {
        return equipamentRepository.findById(equipamentId).orElseThrow(()->new ResourceNotFoundException("Equipament","Id",equipamentId));
    }

    @Override
    public Equipament createEquipament(Equipament equipament) {
        return equipamentRepository.save(equipament);
    }

    @Override
    public Equipament updateEquipament(Long equipamentId, Equipament equipamentDetails) {
        return equipamentRepository.findById(equipamentId).map(equipament -> {
            equipament.setName(equipamentDetails.getName());
            equipament.setDescription(equipamentDetails.getDescription());
            return equipamentRepository.save(equipament);
        }).orElseThrow(()->new ResourceNotFoundException("Equipament","Id",equipamentId));
    }

    @Override
    public ResponseEntity<?> deleteEquipament(Long equipamentId) {
        if(!equipamentRepository.existsById(equipamentId))
            throw new ResourceNotFoundException("Equipament", "Id", equipamentId);

        return equipamentRepository.findById(equipamentId).map(equipament -> {
            equipamentRepository.delete(equipament);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Equipament", "Id", equipamentId));

    }

    @Override
    public Page<Equipament> getAllEquipamentsBySessionId(Long sessionId, Pageable pageable) {
        return sessionRepository.findById(sessionId).map(session -> {
                    List<Equipament> equipaments = session.getEquipaments();
                    int equipamentsCount = equipaments.size();
                    return new PageImpl<>(equipaments, pageable, equipamentsCount);
                }
        ).orElseThrow(() -> new ResourceNotFoundException(
                "Session", "Id", sessionId));
    }
}
