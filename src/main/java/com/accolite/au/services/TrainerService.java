package com.accolite.au.services;

import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.dto.TrainerDTO;

import java.util.Set;

public interface TrainerService {

    TrainerDTO addToBatchOrUpdateTrainer(TrainerDTO trainerDTO);

    Set<TrainerDTO> getAllTrainers(int batchId);

    TrainerDTO getTrainer(int trainerId);

    SuccessResponseDTO deleteTrainer(int trainerId);
}
