package com.accolite.au.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;

@Data
public class ExceptionDetailsDTO {
    private Map<String, String> errors;
    private Timestamp timestamp;
    private HttpStatus status;

    public ExceptionDetailsDTO(Map<String, String> errors, Timestamp timestamp, HttpStatus status) {
        this.errors = errors;
        this.timestamp = timestamp;
        this.status = status;
    }
}
