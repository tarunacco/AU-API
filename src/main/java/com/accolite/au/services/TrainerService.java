package com.accolite.au.services;

import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.dto.TrainerDTO;

import java.util.List;

public interface TrainerService {

    TrainerDTO addToBatchOrUpdateTrainer(TrainerDTO trainerDTO);

    List<TrainerDTO> getAllTrainers();

    TrainerDTO getTrainer(int trainerId);

    SuccessResponseDTO deleteTrainer(int trainerId);
}
