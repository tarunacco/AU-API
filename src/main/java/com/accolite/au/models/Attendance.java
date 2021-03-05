package com.accolite.au.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

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

    public int getAttendaceId() {
        return attendaceId;
    }

    public void setAttendaceId(int attendaceId) {
        this.attendaceId = attendaceId;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
}
