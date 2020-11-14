package com.opense.traininggain.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    @Size(max = 25)
    private String name;

    @NotNull
    @Size(max = 100)
    private String description;



    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy="tags")
    private List<Session> sessions;

    public Tag() {
    }

    public Tag(@NotNull @Size(max = 25) String name, @NotNull @Size(max = 100) String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return Id;
    }

    public Tag setId(Long id) {
        Id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Tag setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Tag setDescription(String description) {
        this.description = description;
        return this;
    }
    public List<Session> getSessions() {
        return sessions;
    }

    public Tag setSessions(List<Session> sessions) {
        this.sessions = sessions;
        return this;
    }
}
