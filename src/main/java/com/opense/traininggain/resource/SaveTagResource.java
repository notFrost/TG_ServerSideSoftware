package com.opense.traininggain.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveTagResource {
    @NotNull
    @Size(max = 25)
    private String name;

    @NotNull
    @Size(max = 100)
    private String description;

    public String getName() {
        return name;
    }

    public SaveTagResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveTagResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
