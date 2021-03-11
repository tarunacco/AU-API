package com.accolite.au.repositories;

import com.accolite.au.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByBatch_BatchIdOrderByFirstNameAsc(int batchId);
}
