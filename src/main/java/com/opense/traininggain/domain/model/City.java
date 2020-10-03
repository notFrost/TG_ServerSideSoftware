package com.opense.traininggain.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="cities")
public class City extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cCity;

    @NotNull
    @Size(max=20)
    private String nCity;

    //@NotNull
    //private Long cCountry;
}
