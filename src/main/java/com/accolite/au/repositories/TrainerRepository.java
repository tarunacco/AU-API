package com.accolite.au.repositories;

import com.accolite.au.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

    List<Trainer> findAllByTrainerId(int batchId);
}
