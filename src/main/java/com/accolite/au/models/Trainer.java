package com.accolite.au.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
public class Trainer implements Serializable {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int trainerId;

    @OneToOne(targetEntity = BusinessUnit.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private BusinessUnit businessUnit;

    @NotNull(message = "trainerName Should be provided")
    private String trainerName;

    private String skypeId;

    @Email(message = "Provide a valid Email")
    private String reportingManagerEmailId;

    @Email(message = "Provide a valid Email")
    private String emailId;

    @CreationTimestamp
    private Timestamp createdOn;

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getSkypeId() {
        return skypeId;
    }

    public void setSkypeId(String skypeId) {
        this.skypeId = skypeId;
    }

    public String getReportingManagerEmailId() {
        return reportingManagerEmailId;
    }

    public void setReportingManagerEmailId(String reportingManagerEmailId) {
        this.reportingManagerEmailId = reportingManagerEmailId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
}
