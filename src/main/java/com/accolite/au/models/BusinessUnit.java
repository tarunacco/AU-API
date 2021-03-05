package com.accolite.au.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class BusinessUnit implements Serializable {
    @Id // --> primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // --> auto generated key
    private int buId;

    @NotNull(message = "BU Name Should be provided")
    private String buName;

    @NotNull(message = "BU Head Name Should be provided")
    private String buHeadName;

    @Email
    private String buHeadEmail;

    @CreationTimestamp
    private Timestamp createdOn;

    public int getBuId() {
        return buId;
    }

    public void setBuId(int buId) {
        this.buId = buId;
    }

    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName;
    }

    public String getBuHeadName() {
        return buHeadName;
    }

    public void setBuHeadName(String buHeadName) {
        this.buHeadName = buHeadName;
    }

    public String getBuHeadEmail() {
        return buHeadEmail;
    }

    public void setBuHeadEmail(String buHeadEmail) {
        this.buHeadEmail = buHeadEmail;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
}