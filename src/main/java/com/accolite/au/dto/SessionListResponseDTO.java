package com.accolite.au.dto;

import com.accolite.au.models.Batch;
import com.accolite.au.models.Session;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class SessionListResponseDTO {
    private Set<Session> sessions;
    private Timestamp timestamp;
    private HttpStatus status;
    private int total_sessions_count;

    public SessionListResponseDTO(Set<Session> sessions, HttpStatus status) {
        System.out.println(sessions);
        this.sessions = sessions;
        this.status = status;
        this.total_sessions_count = sessions.size();
        this.timestamp = new Timestamp(new Date().getTime());
    }
}