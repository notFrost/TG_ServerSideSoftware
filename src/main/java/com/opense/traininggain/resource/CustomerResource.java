package com.opense.traininggain.resource;

import com.opense.traininggain.domain.model.AuditModel;
import com.opense.traininggain.domain.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class CustomerResource extends AuditModel {

    private Long id;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public CustomerResource setDescription(String description) {
        this.description = description;
        return this;
    }

}
