package com.accolite.au.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class SuccessResponseDTO {
    private String message;
    private Timestamp timestamp;
    private HttpStatus status;

    public SuccessResponseDTO(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.timestamp = new Timestamp(new Date().getTime());
    }
}
