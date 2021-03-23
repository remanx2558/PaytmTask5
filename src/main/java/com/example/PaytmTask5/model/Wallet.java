package com.example.PaytmTask5.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

// Wallet entity for wallet table
@Entity
@Table(name = "wallets")
public class Wallet {

    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name = "balance")
    private long balance;


    @Column(name = "mobileWallet")
    private Long mobileWallet;


    @Column(name = "haswallet")
    private boolean haswallet;


    @Column(name = "isCustomer")
    private boolean isCustomer;

    public Wallet() {}

    public Wallet( Long mobileWallet) {

        super();
        this.mobileWallet = mobileWallet;
        this.isCustomer = true;
        this.balance = 0;
        this.haswallet = true;

    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }



    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public Long getMobileWallet() {
        return mobileWallet;
    }

    public void setMobileWallet(long mobileWallet) {
        this.mobileWallet = mobileWallet;
    }



    public boolean isHaswallet() {
        return haswallet;
    }

    public void setHaswallet(boolean haswallet) {
        this.haswallet = haswallet;
    }

    public boolean isCustomer() {
        return isCustomer;
    }

    public void setCustomer(boolean iscustomer) {
        this.isCustomer = iscustomer;
    }


}