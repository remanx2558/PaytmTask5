package com.example.PaytmTask5.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long uid;

    @NotNull
    @Size(min = 2, message = "First Name should have atelast 2 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "mobile")
    private Long mobile;

    @NotNull
    @Size(min = 2, message = "Last Name should have atelast 2 characters")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;


    @Column(name = "haswallet")
    private boolean haswallet;

    @Column(name = "balance")
    private long balance;

    @Column(name = "gender")
    private String gender;


    public User() {
    }

    public User(String firstName, String lastName, String email, String address1, String address2, long mobile, String gender) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.mobile = mobile;
        this.gender = gender;
        this.haswallet = false;
        this.balance = 0;

    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public boolean getHaswallet() {
        return haswallet;
    }

    public void setHaswallet(boolean haswallet) {
        this.haswallet = haswallet;
    }

    public boolean isHaswallet() {
        return haswallet;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}