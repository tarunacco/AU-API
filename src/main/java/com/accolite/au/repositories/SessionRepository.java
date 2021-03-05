package com.accolite.au.repositories;

import com.accolite.au.models.Session;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface SessionRepository extends JpaRepository<Session, Integer> {

    List<Session> findAllByBatch_BatchId(int batchId);
}
