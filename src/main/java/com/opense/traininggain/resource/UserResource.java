package com.opense.traininggain.resource;

import com.opense.traininggain.domain.model.AuditModel;

import java.util.Date;

public class UserResource extends AuditModel {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Long phone;
    private String email;
    private String gender;
    private String password;


    public Long getId() {
        return id;
    }

    public UserResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserResource setFirstName(String name) {
        this.firstName = name;
        return this;
    }

    public String getLastname() {
        return lastName;
    }

    public UserResource setLastname(String lastname) {
        this.lastName = lastname;
        return this;
    }

    public Date getBirth() {
        return birthDate;
    }

    public UserResource setBirth(Date birth) {
        this.birthDate = birth;
        return this;
    }


    public Long getPhone() {
        return phone;
    }

    public UserResource setPhone(Long phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public UserResource setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserResource setPassword(String password) {
        this.password = password;
        return this;
    }

}
