package com.opense.traininggain.resource;

import com.opense.traininggain.domain.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SaveCustomerResource {

    @NotNull
    @Size(max = 30)
    private String description;

    public String getDescription() {
        return description;
    }

    public SaveCustomerResource setDescription(String description) {
        this.description = description;
        return this;
    }

}
