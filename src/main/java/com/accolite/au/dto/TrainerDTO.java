package com.accolite.au.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class TrainerDTO {
    private int trainerId;
    //    private int businessUnitId;
    //private int batchId = Integer.MIN_VALUE;

    private BusinessUnitDTO businessUnit;

    @NotNull(message = "trainerName Should be provided")
    private String trainerName;

    private String skypeId;

    @Email(message = "Provide a valid Email")
    private String reportingManagerEmailId;

    @Email(message = "Provide a valid Email")
    private String emailId;

    private Timestamp createdOn;

    public BusinessUnitDTO getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnitDTO businessUnit) {
        this.businessUnit = businessUnit;
    }

    //    public int getBatchId() {
//        return batchId;
//    }
//
//    public void setBatchId(int batchId) {
//        this.batchId = batchId;
//    }
    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

//    public int getBusinessUnitId() {
//        return businessUnitId;
//    }
//
//    public void setBusinessUnitId(int businessUnitId) {
//        this.businessUnitId = businessUnitId;
//    }

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

    public TrainerDTO(){

    }
    public TrainerDTO(BusinessUnitDTO businessUnit, String trainerName, String skypeId, String reportingManagerEmailId, String emailId) {
        this.businessUnit = businessUnit;
        this.skypeId = skypeId;
        this.trainerName = trainerName;
        this.reportingManagerEmailId = reportingManagerEmailId;
        this.emailId = emailId;
    }
}
