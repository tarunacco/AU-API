package com.accolite.au.repositories;

import com.accolite.au.dto.TrainerDTO;
import com.accolite.au.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

    List<Trainer> findAllByTrainerId(int batchId);

    List<TrainerDTO> findAllByEmailId(@Param("email") String email);
}
