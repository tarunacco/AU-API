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

@RestController
@RequestMapping("/session")
public class SessionController {
    private BatchService batchService;
    private SessionService sessionService;

    @GetMapping({"/getSession"})
    public ResponseEntity<SessionResponseDTO> getSession(@RequestParam(required = true, name="sessionId") int sessionId){
        return new ResponseEntity(sessionService.getSession(sessionId), HttpStatus.OK);
    }

    @PutMapping({"/updateSession/{sessionId}"})
    public ResponseEntity<SessionResponseDTO> updateSession(@PathVariable(required = true, name="batchId") int sessionId, @RequestBody Session session){
        return new ResponseEntity(sessionService.updateSession(sessionId, session), HttpStatus.CREATED);
    }

    @DeleteMapping({"/deleteSession/{sessionId}"})
    public ResponseEntity<SuccessResponseDTO> deleteSession(@PathVariable(required = true, name="sessionId") int sessionId){
        return new ResponseEntity(sessionService.deleteSession(sessionId), HttpStatus.OK);
    }
}
