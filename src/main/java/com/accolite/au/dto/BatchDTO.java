package com.accolite.au.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class BatchDTO {
    private int batchId;
    private String batchName;
    private String commonSkypeId;
    private String commonClassroomId;
    private Date startDate;
    private Date endDate;
    private Timestamp createdOn;
}
