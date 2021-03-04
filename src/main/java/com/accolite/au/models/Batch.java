package com.accolite.au.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Batch {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "batch_id")
    private int batchId;

    private String batchName, commonSkypeId, commonClassroomId;

    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
    private Set<Session> sessions = new HashSet<>();

    private Date startDate, endDate;

    @CreationTimestamp
    private Timestamp createdOn;
}