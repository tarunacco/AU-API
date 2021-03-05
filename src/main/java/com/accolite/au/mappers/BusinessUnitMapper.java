package com.accolite.au.mappers;

import com.accolite.au.dto.BusinessUnitDTO;
import com.accolite.au.models.BusinessUnit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BusinessUnitMapper {

//    @Mapping(target = "trainer", ignore = true)
//    BusinessUnit toBusiness(BusinessUnitDTO businessUnitDTO);

    BusinessUnitDTO toBusinessUnitDTO(BusinessUnit businessUnit);

    List<BusinessUnitDTO> toBusinessUnitDTOs(List<BusinessUnit> businessUnits);
}
