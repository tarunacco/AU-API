package com.accolite.au.repositories;

import com.accolite.au.models.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<StudentGroup, Integer> {

}
