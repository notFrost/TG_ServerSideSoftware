package com.opense.traininggain.domain.model;

import javax.persistence.*;
import javax.print.DocFlavor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "equipaments")
public class Equipament extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    @Size(max = 25)
    private String name;

    @NotNull
    @Size(max = 100)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy="equipaments")
    private List<Session> sessions;


    public Equipament(@NotNull @Size(max = 25) String name, @NotNull @Size(max = 100) String description) {
        this.name = name;
        this.description = description;
    }

    public Equipament() {
    }


    public Long getId() {
        return Id;
    }

    public Equipament setId(Long id) {
        Id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Equipament setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Equipament setDescription(String description) {
        this.description = description;
        return this;
    }
}
