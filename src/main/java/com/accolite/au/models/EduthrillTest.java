package com.accolite.au.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

//@Entity
public class EduthrillTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eduthrillTestId;

    private String testName;

    @CreationTimestamp
    private Timestamp createdOn;
}
