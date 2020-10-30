package com.opense.traininggain.resource;

import java.util.Date;
import com.opense.traininggain.domain.model.AuditModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveSpecialistResource extends AuditModel {
    @NotNull
    @NotBlank
    @Size(max = 25)
    private String specialty;

    public String getSpecialty() {
        return specialty;
    }

    public SaveSpecialistResource setSpecialty(String specialty) {
        this.specialty = specialty;
        return this;
    }
}