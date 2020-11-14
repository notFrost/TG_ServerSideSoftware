package com.opense.traininggain.resource;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveEquipamentResource {

    @NotNull
    @Size(max = 25)
    private String name;

    @NotNull
    @Size(max = 100)
    private String description;

    public String getName() {
        return name;
    }

    public SaveEquipamentResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveEquipamentResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
