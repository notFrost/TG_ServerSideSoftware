package com.opense.traininggain.resource;

import com.opense.traininggain.domain.model.AuditModel;


import java.util.Date;

public class SessionResource extends AuditModel {
    private Long id;
    private String title;
    private Date date;
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

    public String getTitle() {
        return title;
    }

    public SessionResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public SessionResource setDate(Date date) {
        this.date = date;
        return this;
    }
}
