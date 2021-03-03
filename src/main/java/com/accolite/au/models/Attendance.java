package com.accolite.au.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Attendance {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int attendanceId;

    @Id
    @OneToOne(mappedBy = "scheduleId", cascade = CascadeType.ALL)
    private Schedule schedule;

    @Id
    @OneToOne(mappedBy = "studentId", cascade = CascadeType.ALL)
    private Student student;


    private String status;

    @CreationTimestamp
    private Timestamp created_on;
}
