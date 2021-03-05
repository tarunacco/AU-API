package com.accolite.au.services.impl;

import com.accolite.au.dto.BatchDTO;
import com.accolite.au.dto.CustomEntityNotFoundExceptionDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.mappers.BatchMapper;
import com.accolite.au.models.Batch;
import com.accolite.au.repositories.BatchRepository;
import com.accolite.au.services.BatchService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

@Service
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;
    private final BatchMapper batchMapper;
    private final EntityManager entityManager;

    public BatchServiceImpl(BatchRepository batchRepository, BatchMapper batchMapper, EntityManager entityManager) {
        this.batchRepository = batchRepository;
        this.batchMapper = batchMapper;
        this.entityManager = entityManager;
    }

    @Override
    public BatchDTO addBatch(BatchDTO batchDTO) {
        Batch batch = batchMapper.toBatch(batchDTO);
        return batchMapper.toBatchDTO(batchRepository.saveAndFlush(batch));
    }

    @Override
    public List<BatchDTO> getAllBatches(){
        List<Batch> batches = batchRepository.findAllByOrderByBatchName();
        return batchMapper.toBatchDTOs(batches);
    }

    @Override
    public BatchDTO getBatch(int batchId){
        if(batchRepository.existsById(batchId) == false){
            throw new CustomEntityNotFoundExceptionDTO("Batch with id : " + batchId + " not Found");
        }
        return batchMapper.toBatchDTO(batchRepository.getOne(batchId));
    }

    @Override
    public SuccessResponseDTO deleteBatch(int batchId){
        if(batchRepository.existsById(batchId)){
            batchRepository.deleteById(batchId);
            System.out.println("Done");
            return new SuccessResponseDTO("Batch with id : " + batchId + " deleted Successfully", HttpStatus.OK);
        }
        throw new CustomEntityNotFoundExceptionDTO("Batch with id : " + batchId + " not Found");
    }

    @Override
    public BatchDTO updateBatch(BatchDTO batchDTO){
        if(batchRepository.existsById(batchDTO.getBatchId())) {
            Batch batch = batchMapper.toBatch(batchDTO);
            return batchMapper.toBatchDTO(batchRepository.saveAndFlush(batch));
        }
        throw new CustomEntityNotFoundExceptionDTO("Batch with id : " + batchDTO.getBatchId() + " not Found");
    }
}
