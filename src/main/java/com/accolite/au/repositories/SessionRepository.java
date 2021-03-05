package com.accolite.au.repositories;

import com.accolite.au.models.Batch;
import com.accolite.au.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Integer> {

    List<Session> findAllByBatch_BatchId(int batchId);

    List<Session> findAllByBatch_BatchIdOrderByStartDateAscDaySlotDesc(int batchId);
}
