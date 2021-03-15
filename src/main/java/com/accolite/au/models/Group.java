package com.accolite.au.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int groupId;

    private String groupName;

    @ManyToOne
    @JoinColumn(name = "batchId")
    private Batch batch;

    @OneToMany(targetEntity = Student.class, mappedBy = "group", fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();

    @OneToOne(targetEntity = Trainer.class, fetch = FetchType.LAZY)
    @JoinColumn(name="trainerId")
    private Trainer trainer;

    @CreationTimestamp
    private Timestamp createdOn;

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
