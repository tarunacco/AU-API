package com.accolite.au.dto;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Map;

public class ExceptionDetailsDTO {
    private final Map<String, String> errors;
    private final Timestamp timestamp;
    private final HttpStatus status;

    public ExceptionDetailsDTO(Map<String, String> errors, Timestamp timestamp, HttpStatus status) {
        this.errors = errors;
        this.timestamp = timestamp;
        this.status = status;
    }
}
