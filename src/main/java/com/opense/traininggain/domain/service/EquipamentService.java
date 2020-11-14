package com.opense.traininggain.domain.service;

import com.opense.traininggain.domain.model.Customer;
import com.opense.traininggain.domain.model.Equipament;
import com.opense.traininggain.domain.model.Session;
import com.opense.traininggain.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface EquipamentService {
    Page<Equipament> getAllEquipaments(Pageable pageable);
    Equipament getEquipamentById(Long equipamentId);
    Equipament createEquipament(Equipament equipament);
    Equipament updateEquipament(Long equipamentId, Equipament equipamentDetails);
    ResponseEntity<?> deleteEquipament(Long equipamentId);
    Page<Equipament> getAllEquipamentsBySessionId(Long sessionId, Pageable pageable);

}
