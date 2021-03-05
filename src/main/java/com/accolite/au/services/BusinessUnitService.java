package com.accolite.au.services;

import com.accolite.au.dto.BusinessUnitDTO;

import java.util.List;

public interface BusinessUnitService {
    List<BusinessUnitDTO> getAllBusinessUnits();

    BusinessUnitDTO getBusinessUnit(int businessUnitId);
}
