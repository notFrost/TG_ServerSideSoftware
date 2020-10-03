package com.opense.traininggain.resource;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SaveCustomerResource {

    @NotNull
    @NotBlank
    @Size(max = 25)
    private String nCustomer;

    @NotNull
    @NotBlank
    @DateTimeFormat(style = "dd/MM/yyyy")
    private Date nBirthDate;

    @NotNull
    @NotBlank
    private Boolean fMale;

    @NotNull
    @NotBlank
    private Long nPhone;

    @NotNull
    @NotBlank
    @Size(max = 40)
    private String tEmail;

    @NotNull
    @NotBlank
    @Size(max = 40)
    private String tAddress;


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
