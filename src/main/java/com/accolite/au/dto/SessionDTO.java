package com.accolite.au.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class SessionDTO {
    private int sessionId;
    private int batchId;
    private String sessionName;
    private String classroomTopicId;
    private Date startDate;
    private Date endDate;
    private Timestamp createdOn;
}
