package com.accolite.au.services;

import com.accolite.au.dto.BatchDTO;
import com.accolite.au.dto.BatchResponseDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.models.Batch;
import com.accolite.au.models.Session;

import java.util.List;

public interface BatchService {
    BatchResponseDTO addBatch(BatchDTO batch);

    List<BatchDTO> getAllBatches();

    BatchResponseDTO getBatch(int batchId);

    SuccessResponseDTO deleteBatch(int batchId);

    BatchResponseDTO updateBatch(int batchId, Batch batch);

    Session addSessionToBatch(int batchId, Session session);

//    Session addSessionToBatch(int batchId, Session session);

}
