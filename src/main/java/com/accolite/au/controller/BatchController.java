package com.accolite.au.controller;

import com.accolite.au.dto.BatchDTO;
import com.accolite.au.dto.SessionDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.dto.TrainerDTO;
import com.accolite.au.services.BatchService;
import com.accolite.au.services.MailerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/batch")
public class BatchController {
    private final BatchService batchService;
    private final MailerService mailerService;

    public BatchController(BatchService batchService, MailerService mailerService) {
        this.batchService = batchService;
        this.mailerService = mailerService;
    }

    @PostMapping({"/add"})
    public ResponseEntity<BatchDTO> addBatch(@Valid @RequestBody BatchDTO batch) {
        return new ResponseEntity(batchService.addBatch(batch), HttpStatus.CREATED);
    }

    @PutMapping({"/update"})
    public ResponseEntity<BatchDTO> updateBatch(@Valid @RequestBody BatchDTO batch) {
        return new ResponseEntity(batchService.updateBatch(batch), HttpStatus.CREATED);
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<BatchDTO>> getAllBatches(){
        return new ResponseEntity(batchService.getAllBatches(), HttpStatus.OK);
    }

    @GetMapping({"/{batchId}"})
    public ResponseEntity<BatchDTO> getBatch(@PathVariable(required = true, name = "batchId") int batchId){
        return new ResponseEntity(batchService.getBatch(batchId), HttpStatus.OK);
    }

    @DeleteMapping({"/{batchId}"})
    public ResponseEntity<SuccessResponseDTO> deleteBatch(@PathVariable(required = true, name="batchId") int batchId){
        return new ResponseEntity(batchService.deleteBatch(batchId), HttpStatus.OK);
    }

    @PostMapping({"/sendMail"})
    public ResponseEntity<String> sendMail() throws IOException {
        mailerService.SendMockMail();
        return new ResponseEntity("Mail Sent Successfully", HttpStatus.CREATED);
    }
}
