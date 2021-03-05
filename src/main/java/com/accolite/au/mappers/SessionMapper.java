package com.accolite.au.mappers;

import com.accolite.au.dto.SessionDTO;
import com.accolite.au.models.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    @Mappings({@Mapping(target = "batch", ignore = true), @Mapping(source = "sessionDto.batchId", target = "batch.batchId")})
    Session toSession(SessionDTO sessionDto);

    @Mapping(source = "batch.batchId", target = "batchId")
    SessionDTO toSessionDTO(Session session);

    List<SessionDTO> toSessionDTOs(List<Session> sessions);
}
