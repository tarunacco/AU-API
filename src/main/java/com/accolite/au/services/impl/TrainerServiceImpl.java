package com.accolite.au.services.impl;

import com.accolite.au.dto.CustomEntityNotFoundExceptionDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.dto.TrainerDTO;
import com.accolite.au.mappers.TrainerMapper;
import com.accolite.au.models.Batch;
import com.accolite.au.models.Trainer;
import com.accolite.au.repositories.BatchRepository;
import com.accolite.au.repositories.TrainerRepository;
import com.accolite.au.services.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {
    
    private final BatchRepository batchRepository;
    private final TrainerRepository trainerRepository;
    private final TrainerMapper trainerMapper;
    private final EntityManager entityManager;

    public TrainerServiceImpl(BatchRepository batchRepository, TrainerRepository trainerRepository, TrainerMapper trainerMapper, EntityManager entityManager) {
        this.batchRepository = batchRepository;
        this.trainerRepository = trainerRepository;
        this.trainerMapper = trainerMapper;
        this.entityManager = entityManager;
    }

    @Override
    public TrainerDTO addTrainerToBatch(TrainerDTO trainerDTO){
        if(batchRepository.existsById(trainerDTO.getBatchId())) {
            Trainer trainer = trainerMapper.toTrainer(trainerDTO);
            Batch batchReference = entityManager.getReference(Batch.class, trainerDTO.getBatchId());
            trainer.setBatch(batchReference);
            return trainerMapper.toTrainerDTO(trainerRepository.save(trainer));
        }
        throw new CustomEntityNotFoundExceptionDTO("Batch with id : " + trainerDTO.getBatchId() + " not Found");
    }

    @Override
    public List<TrainerDTO> getAllTrainers(int batchId){
        List<Trainer> trainers = trainerRepository.findAllByTrainerId(batchId);
        return trainerMapper.toTrainerDTOs(trainers);
    }

    @Override
    public TrainerDTO getTrainer(int trainerId){
        if(trainerRepository.existsById(trainerId)) {
            Trainer trainer = trainerRepository.getOne(trainerId);
            return trainerMapper.toTrainerDTO(trainer);
        }
        throw new CustomEntityNotFoundExceptionDTO("Trainer with id : " + trainerId + " not Found");
    }

    @Override
    public SuccessResponseDTO deleteTrainer(int trainerId){
        if(trainerRepository.existsById(trainerId)) {
            trainerRepository.deleteById(trainerId);
            return new SuccessResponseDTO("Trainer with id : " + trainerId + " deleted Successfully", HttpStatus.OK);
        }
        throw new CustomEntityNotFoundExceptionDTO("Trainer with id : " + trainerId + " not Found");
    }

    @Override
    public TrainerDTO updateTrainer(TrainerDTO trainerDTO){
        if(trainerRepository.existsById(trainerDTO.getTrainerId())) {
            Trainer trainer = entityManager.getReference(Trainer.class, trainerDTO.getTrainerId());
            return trainerMapper.toTrainerDTO(trainerRepository.saveAndFlush(trainer));
        }
        throw new CustomEntityNotFoundExceptionDTO("Trainer with id : " + trainerDTO.getTrainerId() + " not Found");
    }
}
