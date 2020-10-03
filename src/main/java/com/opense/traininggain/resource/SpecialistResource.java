package com.opense.traininggain.resource;

import java.util.Date;
import com.opense.traininggain.domain.model.AuditModel;

public class SpecialistResource extends AuditModel {

    private Long cSpecialist;

    private String nSpecialist;

    private Date nBirthdate;

    private Boolean fMale;

    private Long nPhone;

    private String tEmail;

    private String tAddress;

    private String tDescription;

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