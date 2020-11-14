package com.opense.traininggain.resource;

import java.util.Date;
import com.opense.traininggain.domain.model.AuditModel;
import com.opense.traininggain.domain.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SpecialistResource extends AuditModel {

    private Long id;
    private String name;
    private String specialty;


    public Long getId() {
        return id;
    }

    public SpecialistResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSpecialty() {
        return specialty;
    }

    public SpecialistResource setSpecialty(String specialty) {
        this.specialty = specialty;
        return this;
    }
    public String getName() {
        return name;
    }

    public SpecialistResource setName(String name) {
        this.name = name;
        return this;
    }

}