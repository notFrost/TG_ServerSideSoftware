package com.opense.traininggain.resource;

import com.opense.traininggain.domain.model.AuditModel;


import java.util.Date;

public class SessionResource extends AuditModel {
    private Long id;
    private String tittle;
    private Date startDate;
    private String description;

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
}
