package com.accolite.au.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Attendance {
    // Composite Key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int attendaceId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name="attendance_id", nullable = false)
    private Session session;

    //    Composite Key End

    private String status;

    @CreationTimestamp
    private Timestamp createdOn;
}
