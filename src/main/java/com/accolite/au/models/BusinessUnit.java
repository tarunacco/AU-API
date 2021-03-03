package com.accolite.au.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data // Lombok takes care of the getters and setters and .toString() automatically
@Entity
public class BusinessUnit {
    @Id // --> primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // --> auto generated key
    private int buId;

    private String buName, buHeadName, buHeadEmail;

    @CreationTimestamp
    private Timestamp createdOn;
}
