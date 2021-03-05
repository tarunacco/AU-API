package com.accolite.au.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Student implements Serializable {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studentId;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    @JsonBackReference
    public Batch getBatch(){
        return batch;
    }

    @NotNull(message = "firstName Should be provided")
    private String firstName;

    @NotNull(message = "lastName Should be provided")
    private String lastName;

    private String skypeId="";

    @Email
    private String emailId;

    @CreationTimestamp
    private Timestamp createdOn;
}
