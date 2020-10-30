package com.opense.traininggain.resource;

import com.opense.traininggain.domain.model.AuditModel;

import java.util.Date;

public class UserResource extends AuditModel {
    private Long id;
    private String name;
    private String lastname;
    private Date birth;
    private String address;
    private Long phone;
    private int age;
    private String email;
    private String gender;
    private String password;
    private String country;


    public Long getId() {
        return id;
    }

    public UserResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public UserResource setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public Date getBirth() {
        return birth;
    }

    public UserResource setBirth(Date birth) {
        this.birth = birth;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserResource setAddress(String address) {
        this.address = address;
        return this;
    }

    public Long getPhone() {
        return phone;
    }

    public UserResource setPhone(Long phone) {
        this.phone = phone;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserResource setAge(int age) {
        this.age = age;
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

    public String getCountry() {
        return country;
    }

    public UserResource setCountry(String country) {
        this.country = country;
        return this;
    }
}
