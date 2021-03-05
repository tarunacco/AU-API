package com.accolite.au.mappers;

import com.accolite.au.dto.SessionDTO;
import com.accolite.au.models.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    @Mapping(source = "batchId", target = "batch.batchId")
    Session toSession(SessionDTO sessionDto);
}
