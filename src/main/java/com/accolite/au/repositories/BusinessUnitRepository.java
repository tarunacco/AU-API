package com.accolite.au.repositories;

import com.accolite.au.dto.BusinessUnitDTO;
import com.accolite.au.models.BusinessUnit;
import com.accolite.au.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessUnitRepository extends JpaRepository<BusinessUnit, Integer> {

    List<BusinessUnitDTO> findAllByBuHeadEmail(@Param("buHeadEmail") String buHeadEmail);
}
