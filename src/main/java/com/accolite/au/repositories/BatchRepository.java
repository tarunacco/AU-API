package com.accolite.au.repositories;

import com.accolite.au.models.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BatchRepository extends JpaRepository<Batch, Integer> {

//    @Modifying
//    @Query("UPDATE Batch b SET b.batchName = :#{tempBatch.batchName} WHEN #{tempBatch.batchName} IS NOT NULL ELSE b.batchName," +
//            "b.startDate = :CASE {#tempBatch.startDate} IS NOT NULL THEN tempBatch.startDate ELSE b.startDate, " +
//            "b.endDate = :CASE {#tempBatch.endDate} IS NOT NULL THEN tempBatch.endDate ELSE b.endDate")
//    public void findAndUpdateObject(@Param("tempBatch") Batch tempBatch);

}
