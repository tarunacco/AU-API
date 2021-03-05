package com.accolite.au.models;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@RequiredArgsConstructor
public class Batch implements Serializable {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int batchId;

    @NotNull(message = "batchName Should be provided")
    private String batchName;


    private String commonSkypeId, commonClassroomId;

    @OneToMany(targetEntity = Student.class, mappedBy = "batch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();

    @JsonManagedReference
    @JsonIgnore
    public Set<Student> getStudents(){
        return students;
    }

    @OneToMany(targetEntity = Session.class, mappedBy = "batch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Session> sessions = new HashSet<>();

    @JsonManagedReference
    @JsonIgnore
    public Set<Session> getSessions(){
        return sessions;
    }

    @NotNull(message = "startDate Should be provided")
    private Date startDate;

    @NotNull(message = "endDate Should be provided")
    private Date endDate;

    @CreationTimestamp
    private Timestamp createdOn;

//    public Batch.SubBatch createSubBatchObject(Batch batch){
//        return new SubBatch(batch.getBatchId(), batch.getBatchName(), batch.getCommonSkypeId(),
//                batch.getCommonClassroomId(), batch.getStartDate(), batch.getEndDate(), batch.getCreatedOn());
//    }

//    @Data
//    public static class SubBatch {
//        private int batchId;
//        private String batchName, commonSkypeId, commonClassroomId;
//        private Date startDate, endDate;
//        private Timestamp createdOn;
//
//        public SubBatch(int batchId, String batchName, String commonSkypeId, String commonClassroomId, Date startDate, Date endDate, Timestamp createdOn) {
//            this.batchId = batchId;
//            this.batchName = batchName;
//            this.commonSkypeId = commonSkypeId;
//            this.commonClassroomId = commonClassroomId;
//            this.startDate = startDate;
//            this.endDate = endDate;
//            this.createdOn = createdOn;
//        }
//    }

}

