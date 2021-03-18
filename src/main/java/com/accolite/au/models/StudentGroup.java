package com.accolite.au.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class StudentGroup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studentGroupId;

    private String studentGroupName;

    @ManyToOne
    @JoinColumn(name = "batchId")
    private Batch batch;

    @OneToMany(targetEntity = Student.class, mappedBy = "studentGroup", fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();

    @OneToOne(targetEntity = Trainer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "trainerId")
    private Trainer trainer;

    @CreationTimestamp
    private Timestamp createdOn;

    public int getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(int studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    public String getStudentGroupName() {
        return studentGroupName;
    }

    public void setStudentGroupName(String studentGroupName) {
        this.studentGroupName = studentGroupName;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
}
