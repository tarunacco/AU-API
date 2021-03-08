package com.accolite.au.dto;

import com.accolite.au.embeddables.AttendanceEmbeddableId;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

public class AttendanceDTO {
    private int id;

    private AttendanceEmbeddableId attendanceId;

    private String status;
    private int marks;


    @CreationTimestamp
    private Timestamp createdOn;

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AttendanceEmbeddableId getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(AttendanceEmbeddableId attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
}
