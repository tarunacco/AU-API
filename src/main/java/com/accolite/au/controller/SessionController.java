package com.accolite.au.controller;

import com.accolite.au.dto.SessionDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.services.BatchService;
import com.accolite.au.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {
    private final BatchService batchService;
    private final SessionService sessionService;

    public SessionController(BatchService batchService, SessionService sessionService) {
        this.batchService = batchService;
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<SessionDTO> getSessions(@RequestParam Integer batchId) {
        return this.sessionService.getSessions(batchId);
    }

    @PostMapping public void addSession(@RequestBody SessionDTO sessionDTO) {
        sessionService.addSession(sessionDTO);
    }

    @DeleteMapping({"/deleteSession/{sessionId}"})
    public ResponseEntity<SuccessResponseDTO> deleteSession(@PathVariable(required = true, name="sessionId") int sessionId){
        return new ResponseEntity(sessionService.deleteSession(sessionId), HttpStatus.OK);
    }
}
