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

    @NotNull(message = "firstName Should be provided")
    private String firstName;

    @NotNull(message = "lastName Should be provided")
    private String lastName;

    private String skypeId;

    @Email
    private String emailId;

    @CreationTimestamp
    private Timestamp createdOn;

    public Batch getBatch(){
        return batch;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSkypeId() {
        return skypeId;
    }

    public void setSkypeId(String skypeId) {
        this.skypeId = skypeId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
}
