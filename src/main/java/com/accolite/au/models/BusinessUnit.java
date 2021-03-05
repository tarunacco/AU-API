package com.accolite.au.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Data // Lombok takes care of the getters and setters and .toString() automatically
@Entity
public class BusinessUnit implements Serializable {
    @Id // --> primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // --> auto generated key
    private int buId;

    @NotNull(message = "BU Name Should be provided")
    private String buName;

    @NotNull(message = "BU Head Name Should be provided")
    private String buHeadName;

    @Email
    private String buHeadEmail;

    @CreationTimestamp
    private Timestamp createdOn;
}