package com.accolite.au.dto;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

public class SessionDTO {
    private int sessionId;
    private int batchId;

    @NotNull(message = "sessionName Should be provided")
    private String sessionName;
    private String classroomTopicId;

    @NotNull(message = "startDate Should be provided")
    private Date startDate;

    @NotNull(message = "endDate Should be provided")
    private Date endDate;
    private Timestamp createdOn;
    private String daySlot;

    public String getDaySlot() {
        return daySlot;
    }

    public void setDaySlot(String daySlot) {
        this.daySlot = daySlot;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getClassroomTopicId() {
        return classroomTopicId;
    }

    public void setClassroomTopicId(String classroomTopicId) {
        this.classroomTopicId = classroomTopicId;
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
