package com.accolite.au.services.impl;

import com.accolite.au.dto.*;
import com.accolite.au.models.Batch;
import com.accolite.au.models.Session;
import com.accolite.au.repositories.BatchRepository;
import com.accolite.au.repositories.SessionRepository;
import com.accolite.au.services.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {
    private final BatchRepository batchRepository;
    private final SessionRepository sessionRepository;

    @Override
    public BatchResponseDTO addBatch(Batch batch) {
        Batch tempBatch = batchRepository.saveAndFlush(batch);
        return new BatchResponseDTO(batch, HttpStatus.CREATED);
    }

    @Override
    public BatchListResponseDTO getAllBatches(){
        return new BatchListResponseDTO(batchRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public BatchResponseDTO getBatch(int batchId){
        if(batchRepository.existsById(batchId) == false){
            throw new CustomEntityNotFoundExceptionDTO("Batch with id : " + batchId + " not Found");
        }
        return new BatchResponseDTO(batchRepository.getOne(batchId), HttpStatus.OK);
    }

    @Override
    public SuccessResponseDTO deleteBatch(int batchId){
        if(batchRepository.existsById(batchId)){
            batchRepository.deleteById(batchId);
            return new SuccessResponseDTO("Batch with id : " + batchId + " deleted Successfully", HttpStatus.OK);
        }
        throw new CustomEntityNotFoundExceptionDTO("Batch with id : " + batchId + " not Found");
    }

    public void updateObject(Batch batch, Batch tempBatch){
        if(tempBatch.getBatchName() != null){
            batch.setBatchName(tempBatch.getBatchName());
        }
        if(tempBatch.getStartDate() != null){
            batch.setStartDate(tempBatch.getStartDate());
        }
        if(tempBatch.getEndDate() != null){
            batch.setEndDate(tempBatch.getEndDate());
        }
        if(tempBatch.getCommonSkypeId() != null){
            batch.setCommonSkypeId(tempBatch.getCommonSkypeId());
        }
        if(tempBatch.getCommonClassroomId() != null){
            batch.setCommonClassroomId(tempBatch.getCommonClassroomId());
        }
    }

    @Override
    public BatchResponseDTO updateBatch(int batchId, Batch batch){
        if(batchRepository.existsById(batchId)){
            //            batchRepository.findAndUpdateObject((Batch)tempBatch, Batch batch);
            Batch tempBatch = batchRepository.getOne(batchId);
            this.updateObject(tempBatch, batch);
            return new BatchResponseDTO(tempBatch, HttpStatus.CREATED);
        }
        throw new CustomEntityNotFoundExceptionDTO("Batch with id : " + batchId + " not Found");
    }

    @Override
    public Session addSessionToBatch(int batchId, Session session){
        Session tempSession = batchRepository.findById(batchId).map(batch -> {
            System.out.println(batch);
            session.setBatch(batch);
            return sessionRepository.saveAndFlush(session);
        }).orElseThrow(() -> new CustomEntityNotFoundExceptionDTO("Batch with id : " + batchId + " not Found"));
        System.out.println("Hello" + tempSession);
        return tempSession;
    }
}
