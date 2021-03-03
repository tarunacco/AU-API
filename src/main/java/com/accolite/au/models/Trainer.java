package com.accolite.au.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Trainer {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int trainerId;


    @OneToOne(mappedBy="buId", cascade = CascadeType.ALL)
    private BusinessUnit businessUnit;

    private String trainerName, skypeId, emailId, reportingManagerEmailId;

    @CreationTimestamp
    private Timestamp createdOn;
}
