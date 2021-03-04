package com.accolite.au.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data // Lombok takes care of the getters and setters and .toString() automatically
@Entity
public class BusinessUnit {
    @Id // --> primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // --> auto generated key
    @Column(name = "bu_id")
    private int buId;

    @OneToOne(mappedBy="businessUnit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Trainer trainer;

    private String buName, buHeadName, buHeadEmail;

    @CreationTimestamp
    private Timestamp createdOn;
}