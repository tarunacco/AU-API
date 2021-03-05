package com.accolite.au.controller;

import com.accolite.au.dto.SessionDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping({"/add"})
    public ResponseEntity<SessionDTO> addSession(@Valid @RequestBody SessionDTO sessionDTO) {
        return new ResponseEntity(sessionService.addOrUpdateSession(sessionDTO), HttpStatus.CREATED);
    }

    @PutMapping({"/update"})
    public ResponseEntity<SessionDTO> updateSession(@Valid @RequestBody SessionDTO sessionDTO) {
        return new ResponseEntity(sessionService.addOrUpdateSession(sessionDTO), HttpStatus.CREATED);
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<SessionDTO>> getAllSessions(@RequestParam(required = true, name = "batchId") int batchId){
        return new ResponseEntity(sessionService.getAllSessions(batchId), HttpStatus.OK);
    }

    @GetMapping({"/{sessionId}"})
    public ResponseEntity<SessionDTO> getSession(@PathVariable(required = true, name = "sessionId") int sessionId){
        return new ResponseEntity(sessionService.getSession(sessionId), HttpStatus.OK);
    }

    @DeleteMapping({"/{sessionId}"})
    public ResponseEntity<SuccessResponseDTO> deleteSession(@PathVariable(required = true, name="sessionId") int sessionId){
        return new ResponseEntity(sessionService.deleteSession(sessionId), HttpStatus.OK);
    }
}
