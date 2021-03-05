package com.accolite.au.controller;

import com.accolite.au.dto.TrainerDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.services.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping({"/add"})
    public ResponseEntity<TrainerDTO> addTrainerToBatch(@Valid @RequestBody TrainerDTO trainerDTO) {
        return new ResponseEntity(trainerService.addTrainerToBatch(trainerDTO), HttpStatus.CREATED);
    }

    @PutMapping({"/update"})
    public ResponseEntity<TrainerDTO> updateTrainer(@Valid @RequestBody TrainerDTO trainerDTO) {
        return new ResponseEntity(trainerService.updateTrainer(trainerDTO), HttpStatus.CREATED);
    }

    @GetMapping({"/all?batchId={batchId}"})
    public ResponseEntity<List<TrainerDTO>> getAllTrainers(@RequestParam(required = true, name = "batchId") int batchId){
        return new ResponseEntity(trainerService.getAllTrainers(batchId), HttpStatus.OK);
    }

    @GetMapping({"/{trainerId}"})
    public ResponseEntity<TrainerDTO> getTrainer(@PathVariable(required = true, name = "trainerId") int trainerId){
        return new ResponseEntity(trainerService.getTrainer(trainerId), HttpStatus.OK);
    }

    @DeleteMapping({"/{trainerId}"})
    public ResponseEntity<SuccessResponseDTO> deleteTrainer(@PathVariable(required = true, name="trainerId") int trainerId){
        return new ResponseEntity(trainerService.deleteTrainer(trainerId), HttpStatus.OK);
    }
}
