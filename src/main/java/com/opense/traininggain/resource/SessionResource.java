package com.opense.traininggain.resource;

import com.opense.traininggain.domain.model.AuditModel;
import com.opense.traininggain.domain.model.Session;
import com.opense.traininggain.domain.model.User;


import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SessionResource extends AuditModel {
    private Long id;
    private String title;
    private Date startDate;
    private String description;



    private String name;

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

    public String getTitle() {
        return title;
    }

    public SessionResource setTitle(String title) {
        this.title = title;
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
