package com.example.PaytmTask5.model;

import javax.persistence.*;

// Transaction entity for transaction table

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long uid;


    @Column(name = "receiver")
    private Long receiver;


    @Column(name = "sender")
    private Long sender;


    @Column(name = "amount")
    private Long amount;


    @Column(name = "status")
    private boolean status;


    @Column(name = "mode")
    private String mode;

    @Column(name = "time")
    private String time;

    private long senderId;
    


    public Transaction() {
    }

    public Transaction(long sender, long receiver, long amount) {
        super();
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    @Override
    public String toString() {
        return "Transaction{" +
                "withuser=" + receiver +
                ", amount=" + amount +
                ", user=" + sender +
                ", id='" + uid + '\'' +
                ", mode='" + mode + '\'' +
                ", status='" + status + '\'' +
                ", time=" + time +
                '}';
    }
}