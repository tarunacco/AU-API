package com.accolite.au.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class ErrorsResponseDTO {
    private String errorMessage;
    private Timestamp timestamp;
    private HttpStatus status;

    public ErrorsResponseDTO(String errorMessage, HttpStatus status) {
        this.errorMessage = errorMessage;
        this.status = status;
        this.timestamp = new Timestamp(new Date().getTime());
    }
}
