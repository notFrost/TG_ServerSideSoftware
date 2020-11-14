package com.opense.traininggain.resource;

import com.opense.traininggain.domain.model.AuditModel;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EquipamentResource extends AuditModel {

    private Long Id;
    private String name;
    private String description;

    public Long getId() {
        return Id;
    }

    public EquipamentResource setId(Long id) {
        Id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public EquipamentResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EquipamentResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
