package com.accolite.au.dto;

import com.accolite.au.models.Session;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Date;

public class SessionResponseDTO {
    private Session session;
    private HttpStatus status;
    private Timestamp timestamp;

    public SessionResponseDTO(Session session, HttpStatus status) {
        this.session = session;
        this.status = status;
        this.timestamp = new Timestamp(new Date().getTime());
    }
}
