package com.opense.traininggain.domain.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="customers")
public class Customer extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cCustomer;

    @NotNull
    @Size(max=25)
    private String nCustomer;

    @NotNull
    @DateTimeFormat(style = "dd/MM/yyyy")
    private Date nBirthDate;

    @NotNull
    private Boolean fMale;

    @NotNull
    private Long nPhone;

  /*  @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "cCity",nullable = false)
    @JsonIgnore
    private City cCity;
*/

    @NotNull
    @Size(max = 40)
    private String tEmail;

    @NotNull
    @Size(max = 40)
    private String tAddress;

    public Customer(Long cCustomer, @NotNull @Size(max = 25) String nCustomer, @NotNull Date nBirthDate, @NotNull Boolean fMale, @NotNull Long nPhone, @NotNull String tEmail, @NotNull String tAddress) {
        this.cCustomer = cCustomer;
        this.nCustomer = nCustomer;
        this.nBirthDate = nBirthDate;
        this.fMale = fMale;
        this.nPhone = nPhone;
        this.tEmail = tEmail;
        this.tAddress = tAddress;
    }

    public Customer() {

    }


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
