package com.opense.traininggain.resource;

import com.opense.traininggain.domain.model.AuditModel;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TagResource extends AuditModel {


    private Long Id;
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public TagResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TagResource setDescription(String description) {
        this.description = description;
        return this;
    }
    public Long getId() {
        return Id;
    }

    public TagResource setId(Long id) {
        Id = id;
        return this;
    }
}
