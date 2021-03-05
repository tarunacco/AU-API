package com.accolite.au.controller;

import com.accolite.au.dto.BatchDTO;
import com.accolite.au.dto.BatchResponseDTO;
import com.accolite.au.dto.SessionListResponseDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.models.Batch;
import com.accolite.au.models.Session;
import com.accolite.au.services.BatchService;
import com.accolite.au.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/batch")
public class BatchController {
    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping()
    public void addBatch(@Valid @RequestBody BatchDTO batch) {
        batchService.addBatch(batch);
    }

    @GetMapping("all")
    public List<BatchDTO> getAllBatches(){
        return batchService.getAllBatches();
    }


    @DeleteMapping({"/deleteBatch/{batchId}"})
    public ResponseEntity<SuccessResponseDTO> deleteBatch(@PathVariable(required = true, name="batchId") int batchId){
        return new ResponseEntity(batchService.deleteBatch(batchId), HttpStatus.OK);
    }

}
