package com.opense.traininggain.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="countries")
public class Country extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cCountry;

    @NotNull
    @Size(max=20)
    private String nCountry;
}