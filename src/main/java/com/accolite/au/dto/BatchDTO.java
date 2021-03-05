package com.accolite.au.dto;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

public class BatchDTO {
    private int batchId;

    @NotNull(message = "batchName Should be provided")
    private String batchName;

    private String commonSkypeId;
    private String commonClassroomId;

    @NotNull(message = "startDate Should be provided")
    private Date startDate;

    @NotNull(message = "endDate Should be provided")
    private Date endDate;
    private Timestamp createdOn;

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getCommonSkypeId() {
        return commonSkypeId;
    }

    public void setCommonSkypeId(String commonSkypeId) {
        this.commonSkypeId = commonSkypeId;
    }

    public String getCommonClassroomId() {
        return commonClassroomId;
    }

    public void setCommonClassroomId(String commonClassroomId) {
        this.commonClassroomId = commonClassroomId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
}
