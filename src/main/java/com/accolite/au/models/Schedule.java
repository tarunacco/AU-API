package com.accolite.au.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
public class Schedule {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int scheduleId;

    @Id
    @OneToOne(mappedBy = "batchId", cascade = CascadeType.ALL)
    private Batch batch;

    @Id
    @OneToOne(mappedBy = "sessionId", cascade = CascadeType.ALL)
    private Session session;

    @Id
    @OneToOne(mappedBy = "trainerId", cascade = CascadeType.ALL)
    private Trainer trainer;

    private Timestamp emailInviteTMSTP, calInviteTMSTP;

    private String calInviteLink;

    @Column(columnDefinition = "default 'false'")
    private boolean isAttendanceMarked;

    private Date heldOn; // date for session

    private String dayHalf; // Morning or Afternoon

    @CreationTimestamp
    private Timestamp createdOn;
}
