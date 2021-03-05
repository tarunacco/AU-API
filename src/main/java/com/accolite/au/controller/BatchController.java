package com.accolite.au.controller;

import com.accolite.au.dto.*;
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
    private BatchService batchService;
    private SessionService sessionService;

    @PostMapping({"/addBatch"})
    public ResponseEntity<BatchResponseDTO> addBatch(@Valid @RequestBody BatchDTO batch) {
        return new ResponseEntity(batchService.addBatch(batch), HttpStatus.CREATED);
    }

    @GetMapping({"/getAllBatches"})
    public ResponseEntity<BatchListResponseDTO> getAllBatches(){
        return new ResponseEntity(batchService.getAllBatches(), HttpStatus.OK);
    }

    @GetMapping({"/getBatch"})
    public ResponseEntity<BatchResponseDTO> getBatch(@RequestParam(required = true, name="batchId") int batchId){
        return new ResponseEntity(batchService.getBatch(batchId), HttpStatus.OK);
    }

    @PutMapping({"/updateBatch/{batchId}"})
    public ResponseEntity<BatchResponseDTO> updateBatch(@PathVariable(required = true, name="batchId") int batchId, @RequestBody Batch batch){
        return new ResponseEntity(batchService.updateBatch(batchId, batch), HttpStatus.CREATED);
    }

    @DeleteMapping({"/deleteBatch/{batchId}"})
    public ResponseEntity<SuccessResponseDTO> deleteBatch(@PathVariable(required = true, name="batchId") int batchId){
        return new ResponseEntity(batchService.deleteBatch(batchId), HttpStatus.OK);
    }

    @PostMapping({"/addSessionToBatch/{batchId}"})
    public ResponseEntity<Session> addSessionToBatch(@PathVariable(required = true, name="batchId") int batchId, @Valid @RequestBody Session session){
        System.out.println("hola"+session);
        Session tempSession = batchService.addSessionToBatch(batchId, session);
        System.out.println("Heelo"+tempSession);
        return new ResponseEntity(tempSession, HttpStatus.CREATED);
    }

    @GetMapping({"/getAllSessions/{batchId}"})
    public ResponseEntity<SessionListResponseDTO> getAllSessions(@PathVariable(required = true, name="batchId") int batchId){
        return new ResponseEntity(sessionService.getAllSessions(batchId), HttpStatus.OK);
    }

}
