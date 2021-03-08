package com.accolite.au.repositories;

import com.accolite.au.embeddables.TrainingEmbeddableId;
import com.accolite.au.models.Training;
import com.accolite.au.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, TrainingEmbeddableId> {

    @Query("SELECT DISTINCT student FROM Training")
    List<Student> findAllDistinctStudents();

    @Query("SELECT session.sessionId, session.sessionName, status, marks FROM Training WHERE student.studentId = :studentId")
    List<String[]> findAllSessionsForStudent(@Param("studentId") int studentId);
}
