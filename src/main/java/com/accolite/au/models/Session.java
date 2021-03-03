package com.accolite.au.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Session {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sessionId;

    private String sessionName, classroomTopicId;

    @ManyToOne
    private Batch batch;

    @CreationTimestamp
    private Timestamp created_on;
}
