package com.opense.traininggain.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SaveUserResource  {
    @NotNull
    @NotBlank
    @Size(max = 15)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 15)
    private String lastname;

    @NotNull
    @NotBlank
    private Date birth;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String address;

    @NotNull
    @NotBlank
    private Long phone;

    @NotNull
    @NotBlank
    private int age;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String gender;

    @NotNull
    @NotBlank
    @Size(max = 8)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 25)
    private String country;


    public String getName() {
        return name;
    }

    public SaveUserResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public SaveUserResource setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public Date getBirth() {
        return birth;
    }

    public SaveUserResource setBirth(Date birth) {
        this.birth = birth;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SaveUserResource setAddress(String address) {
        this.address = address;
        return this;
    }

    public Long getPhone() {
        return phone;
    }

    public SaveUserResource setPhone(Long phone) {
        this.phone = phone;
        return this;
    }

    public int getAge() {
        return age;
    }

    public SaveUserResource setAge(int age) {
        this.age = age;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SaveUserResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public SaveUserResource setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SaveUserResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public SaveUserResource setCountry(String country) {
        this.country = country;
        return this;
    }


}
