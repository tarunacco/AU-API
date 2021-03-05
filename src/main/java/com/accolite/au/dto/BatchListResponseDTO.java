package com.accolite.au.dto;

import com.accolite.au.models.Batch;
import com.accolite.au.models.SubBatch;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class BatchListResponseDTO {
    private List<Batch> batches;
    private Timestamp timestamp;
    private HttpStatus status;
    private int total_batch_count;

    public BatchListResponseDTO(List<Batch> batches, HttpStatus status) {
        this.batches = batches;
        this.status = status;
        this.total_batch_count = batches.size();
        this.timestamp = new Timestamp(new Date().getTime());
    }
}
