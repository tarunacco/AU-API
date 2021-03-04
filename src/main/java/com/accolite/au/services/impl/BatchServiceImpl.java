package com.accolite.au.services.impl;

import com.accolite.au.models.Batch;
import com.accolite.au.repositories.BatchRepository;
import com.accolite.au.services.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {
    private final BatchRepository batchRepository;

    @Override
    public void addBatch(Batch batch) {
        batchRepository.saveAndFlush(batch);
    }

}
