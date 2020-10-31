package com.opense.traininggain.resource;

import org.hibernate.annotations.NaturalId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SaveSessionResource {
    @NotNull
    @Size(max = 30)
    private String tittle;
    @NotNull
    @Size(max = 50)
    private Date startDate;
    @NotNull
    @Size(max=50)
    private String description;

    public String getDescription() {
        return description;
    }

    public SaveSessionResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getTittle() {
        return tittle;
    }

    public SaveSessionResource setTittle(String tittle) {
        this.tittle = tittle;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public SaveSessionResource setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }
}
