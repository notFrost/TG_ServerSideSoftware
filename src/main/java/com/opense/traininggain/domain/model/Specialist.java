package com.opense.traininggain.domain.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="specialists")
public class Specialist extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cSpecialist;

    @NotNull
    @Size(max=25)
    private String nSpecialist;

/*
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cCity", nullable = false)
    private City cCity_FK;
*/

    @NotNull
    private Boolean fMale;

    @NotNull
    @DateTimeFormat(style = "dd/mm/yyyy")
    private Date dBirthdate;

    @NotNull
    @Size(max=40)
    private String tAddress;

    @NotNull
    private Long nPhone;

    @NotNull
    @Size(max=40)
    private String tEmail;

    @NotNull
    @Size(max=200)
    private String tDescription;

    public Specialist(Long cSpecialist, @NotNull @Size(max = 25) String nSpecialist, @NotNull Boolean fMale, @NotNull Date dBirthdate, @NotNull @Size(max = 40) String tAddress, @NotNull Long nPhone, @NotNull @Size(max = 40) String tEmail, @NotNull @Size(max = 200) String tDescription) {
        this.cSpecialist = cSpecialist;
        this.nSpecialist = nSpecialist;
        this.fMale = fMale;
        this.dBirthdate = dBirthdate;
        this.tAddress = tAddress;
        this.nPhone = nPhone;
        this.tEmail = tEmail;
        this.tDescription = tDescription;
    }

    public Specialist() {

    }

    public Long getcSpecialist() {
        return cSpecialist;
    }

    public void setcSpecialist(Long cSpecialist) {
        this.cSpecialist = cSpecialist;
    }

    public String getnSpecialist() {
        return nSpecialist;
    }

    public void setnSpecialist(String nSpecialist) {
        this.nSpecialist = nSpecialist;
    }

    public Boolean getfMale() {
        return fMale;
    }

    public void setfMale(Boolean fMale) {
        this.fMale = fMale;
    }

    public Date getdBirthdate() {
        return dBirthdate;
    }

    public void setdBirthdate(Date dBirthdate) {
        this.dBirthdate = dBirthdate;
    }

    public String gettAddress() {
        return tAddress;
    }

    public void settAddress(String tAddress) {
        this.tAddress = tAddress;
    }

    public Long getnPhone() {
        return nPhone;
    }

    public void setnPhone(Long nPhone) {
        this.nPhone = nPhone;
    }

    public String gettEmail() {
        return tEmail;
    }

    public void settEmail(String tEmail) {
        this.tEmail = tEmail;
    }

    public String gettDescription() {
        return tDescription;
    }

    public void settDescription(String tDescription) {
        this.tDescription = tDescription;
    }
}
