package com.opense.traininggain.resource;

import java.util.Date;
import com.opense.traininggain.domain.model.AuditModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveSpecialistResource extends AuditModel {

    @NotNull
    @NotBlank
    @Size(max=25)
    private String nSpecialist;

    @NotNull
    @NotBlank
    private Date nBirthdate;

    @NotNull
    @NotBlank
    private Boolean fMale;

    @NotNull
    @NotBlank
    private Long nPhone;

    @NotNull
    @NotBlank
    @Size(max=40)
    private String tEmail;

    @NotNull
    @NotBlank
    @Size(max=40)
    private String tAddress;

    @NotNull
    @NotBlank
    @Size(max=200)
    private String tDescription;


    public String getnSpecialist() {
        return nSpecialist;
    }

    public void setnSpecialist(String nSpecialist) {
        this.nSpecialist = nSpecialist;
    }

    public Date getnBirthdate() {
        return nBirthdate;
    }

    public void setnBirthdate(Date nBirthdate) {
        this.nBirthdate = nBirthdate;
    }

    public Boolean getfMale() {
        return fMale;
    }

    public void setfMale(Boolean fMale) {
        this.fMale = fMale;
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

    public String gettAddress() {
        return tAddress;
    }

    public void settAddress(String tAddress) {
        this.tAddress = tAddress;
    }

    public String gettDescription() {
        return tDescription;
    }

    public void settDescription(String tDescription) {
        this.tDescription = tDescription;
    }
}