package com.opense.traininggain.resource;

import com.opense.traininggain.domain.model.AuditModel;


import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SessionResource extends AuditModel {
    private Long id;
    private String tittle;
    private Date startDate;
    private String description;


    private Date startHour;
    private Date endHour;


    public String getDescription() {
        return description;
    }

    public SessionResource setDescription(String description) {
        this.description = description;
        return this;
    }


    public Long getId() {
        return id;
    }

    public SessionResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTittle() {
        return tittle;
    }

    public SessionResource setTittle(String tittle) {
        this.tittle = tittle;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public SessionResource setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }
    public Date getStartHour() {
        return startHour;
    }

    public SessionResource setStartHour(Date startHour) {
        this.startHour = startHour;
        return this;
    }

    public Date getEndHour() {
        return endHour;
    }

    public SessionResource setEndHour(Date endHour) {
        this.endHour = endHour;
        return this;
    }

}
