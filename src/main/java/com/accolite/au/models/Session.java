package com.accolite.au.models;

import com.accolite.au.dto.BatchResponseDTO;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@RequiredArgsConstructor
public class Session implements Serializable {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sessionId;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    @JsonBackReference
    @JsonIgnore
    public Batch getBatch(){
        return batch;
    }

    @NotNull(message = "sessionName Should be provided")
    private String sessionName;

    private String classroomTopicId;

    @NotNull(message = "startDate Should be provided")
    private Date startDate;

    @NotNull(message = "endDate Should be provided")
    private Date endDate;

    @CreationTimestamp
    private Timestamp createdOn;
}
