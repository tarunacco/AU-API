package com.accolite.au.services;

import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.dto.TrainerDTO;

import java.util.List;

public interface TrainerService {

    TrainerDTO addTrainerToBatch(TrainerDTO trainerDTO);

    List<TrainerDTO> getAllTrainers(int batchId);

    TrainerDTO getTrainer(int trainerId);

    SuccessResponseDTO deleteTrainer(int trainerId);

    TrainerDTO updateTrainer(TrainerDTO trainerDTO);
}
