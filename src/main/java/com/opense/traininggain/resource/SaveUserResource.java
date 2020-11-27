package com.opense.traininggain.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SaveUserResource  {
    @NotNull
    @NotBlank
    @Size(max = 15)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(max = 15)
    private String lastName;

    private Date birthDate;

    private Long phone;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 8)
    private String password;


    public String getFirstName() {
        return firstName;
    }

    public SaveUserResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SaveUserResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public SaveUserResource setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Long getPhone() {
        return phone;
    }

    public SaveUserResource setPhone(Long phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SaveUserResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SaveUserResource setPassword(String password) {
        this.password = password;
        return this;
    }
}
