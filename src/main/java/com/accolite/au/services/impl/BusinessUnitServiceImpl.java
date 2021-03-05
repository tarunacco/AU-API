package com.accolite.au.services.impl;

import com.accolite.au.dto.BusinessUnitDTO;
import com.accolite.au.dto.CustomEntityNotFoundExceptionDTO;
import com.accolite.au.mappers.BusinessUnitMapper;
import com.accolite.au.repositories.BusinessUnitRepository;
import com.accolite.au.services.BusinessUnitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessUnitServiceImpl implements BusinessUnitService {
    private final BusinessUnitRepository businessUnitRepository;
    private final BusinessUnitMapper businessUnitMapper;

    public BusinessUnitServiceImpl(BusinessUnitRepository businessUnitRepository, BusinessUnitMapper businessUnitMapper) {
        this.businessUnitRepository = businessUnitRepository;
        this.businessUnitMapper = businessUnitMapper;
    }

    @Override
    public List<BusinessUnitDTO> getAllBusinessUnits(){
        return businessUnitMapper.toBusinessUnitDTOs(businessUnitRepository.findAll());
    }

    @Override
    public BusinessUnitDTO getBusinessUnit(int businessUnitId){
        if(businessUnitRepository.existsById(businessUnitId)) {
            return businessUnitMapper.toBusinessUnitDTO(businessUnitRepository.getOne(businessUnitId));
        }
        throw new CustomEntityNotFoundExceptionDTO("Business Unit with id : " + businessUnitId + " not Found");
    }
}
