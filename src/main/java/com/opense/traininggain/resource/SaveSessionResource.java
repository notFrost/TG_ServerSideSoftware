package com.opense.traininggain.resource;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SaveSessionResource {
    @NotNull
    @Size(max = 30)
    private String title;

    @NotNull
    @Size(max = 50)
    private Date startDate;

    @NotNull
    @Size(max = 5)
    private Date startHour;

    @NotNull
    @Size(max = 5)
    private Date endHour;

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

    public String getTitle() {
        return title;
    }

    public SaveSessionResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public SaveSessionResource setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getStartHour() {
        return startHour;
    }

    public SaveSessionResource setStartHour(Date startHour) {
        this.startHour = startHour;
        return this;
    }

    public Date getEndHour() {
        return endHour;
    }

    public SaveSessionResource setEndHour(Date endHour) {
        this.endHour = endHour;
        return this;
    }



}
