package com.opense.traininggain.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SaveSessionResource {
    @NotNull
    @Size(max = 30)
    private String title;

    @NotNull
    @Size(max = 50)
    private Date date;


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

    public Date getDate() {
        return date;
    }

    public SaveSessionResource setDate(Date date) {
        this.date = date;
        return this;
    }
}
