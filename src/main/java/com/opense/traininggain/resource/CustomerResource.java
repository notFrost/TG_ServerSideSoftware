package com.opense.traininggain.resource;

import com.opense.traininggain.domain.model.AuditModel;

import java.util.Date;

public class CustomerResource extends AuditModel {

    private Long cCustomer;

    private String nCustomer;

    private Date nBirthDate;

    private Boolean fMale;

    private Long nPhone;

    private String tEmail;

    private String tAddress;

    public Long getcCustomer() {
        return cCustomer;
    }

    public void setcCustomer(Long cCustomer) {
        this.cCustomer = cCustomer;
    }

    public String getnCustomer() {
        return nCustomer;
    }

    public void setnCustomer(String nCustomer) {
        this.nCustomer = nCustomer;
    }

    public Date getnBirthDate() {
        return nBirthDate;
    }

    public void setnBirthDate(Date nBirthDate) {
        this.nBirthDate = nBirthDate;
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
}
