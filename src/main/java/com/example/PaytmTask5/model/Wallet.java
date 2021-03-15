package com.example.PaytmTask5.model;

import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Wallet entity for wallet table
@Entity
public class Wallet {

    private long id, balance, owner;
    private String creation;

    public Wallet() {}

    public Wallet(long id, long balance, long owner, String creation) {
        this.id = id;
        this.balance = balance;
        this.owner = owner;
        this.creation = creation;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getCreation() {
        return creation;
    }

    public void setCreation(String creation) {
        this.creation = creation;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", balance=" + balance +
                ", owner=" + owner +
                ", creation='" + creation + '\'' +
                '}';
    }
}