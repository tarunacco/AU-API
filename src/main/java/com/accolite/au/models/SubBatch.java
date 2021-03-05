package com.accolite.au.models;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class SubBatch {
    private int batchId;
    private String batchName, commonSkypeId, commonClassroomId;
    private Date startDate, endDate;
    private Timestamp createdOn;

    public SubBatch(int batchId, String batchName, String commonSkypeId, String commonClassroomId, Date startDate, Date endDate, Timestamp createdOn) {
        this.batchId = batchId;
        this.batchName = batchName;
        this.commonSkypeId = commonSkypeId;
        this.commonClassroomId = commonClassroomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdOn = createdOn;
    }
}
