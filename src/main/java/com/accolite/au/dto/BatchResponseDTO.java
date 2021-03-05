package com.accolite.au.dto;

import com.accolite.au.models.Batch;
import com.accolite.au.models.SubBatch;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class BatchResponseDTO {
    private Batch batch;
    private HttpStatus status;
    private Timestamp timestamp;

    public BatchResponseDTO(Batch batch, HttpStatus status) {
        System.out.println(batch);
        this.batch = batch;
        this.status = status;
        this.timestamp = new Timestamp(new Date().getTime());
    }
}
