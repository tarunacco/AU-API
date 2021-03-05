package com.accolite.au.models;

import lombok.Data;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
public class Trainer implements Serializable {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int trainerId;


    @OneToOne(targetEntity = BusinessUnit.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private BusinessUnit businessUnit;

    @NotNull(message = "trainerName Should be provided")
    private String trainerName;

    private String skypeId = "";

    @Email(message = "Provide a valid Email")
    private String reportingManagerEmailId;

    @Email(message = "Provide a valid Email")
    private String emailId;

    @CreationTimestamp
    private Timestamp createdOn;
}
