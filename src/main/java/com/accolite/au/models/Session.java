package com.accolite.au.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Session implements Serializable {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sessionId;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    @NotNull(message = "sessionName Should be provided")
    private String sessionName;

    private String classroomTopicId;

    @NotNull(message = "startDate Should be provided")
    private Date startDate;

    @NotNull(message = "endDate Should be provided")
    private Date endDate;

    @CreationTimestamp
    private Timestamp createdOn;

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
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
