package com.accolite.au.repositories;

import com.accolite.au.models.BusinessUnit;
import com.accolite.au.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessUnitRepository extends JpaRepository<BusinessUnit, Integer> {

}
