package com.opense.traininggain.resource;

import com.opense.traininggain.domain.model.AuditModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TagResource extends AuditModel {

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
}
