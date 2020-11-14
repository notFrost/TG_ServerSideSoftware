package com.opense.traininggain.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="sessions")
public class Session extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=30)
    private String tittle;

    @NotNull
    @Size(max=50)
    private String description;

    @NotNull
    @Size(max = 50)
    @Column(name = "start_date")
    private Date startDate;


    @NotNull
    @Size(max = 5)
    @Column(name = "start_hour")
    private Date startHour;

    @NotNull
    @Size(max = 5)
    @Column(name = "end_hour")
    private Date endHour;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "specialist_id",nullable = false)
    @JsonIgnore
    private Specialist specialist;


    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy="sessions")
    private List<Customer> customers;

    public Long getId() {
        return id;
    }

    public Session setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTittle() {
        return tittle;
    }

    public Session setTittle(String tittle) {
        this.tittle = tittle;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Session setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }


    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }

    public String getDescription() {
        return description;
    }

    public Session setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getStartHour() {
        return startHour;
    }

    public Session setStartHour(Date startHour) {
        this.startHour = startHour;
        return this;
    }

    public Date getEndHour() {
        return endHour;
    }

    public Session setEndHour(Date endHour) {
        this.endHour = endHour;
        return this;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Session setCustomers(List<Customer> customers) {
        this.customers = customers;
        return this;
    }
}
