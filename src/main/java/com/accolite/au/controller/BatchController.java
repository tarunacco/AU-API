package com.accolite.au.controller;

import com.accolite.au.models.Batch;
import com.accolite.au.services.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("batch")
@RequiredArgsConstructor
public class BatchController {
    private final BatchService batchService;

    @PostMapping
    public void addBatch(@RequestBody Batch batch) {
        batchService.addBatch(batch);
    }
}
