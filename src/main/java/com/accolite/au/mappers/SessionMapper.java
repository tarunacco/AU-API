package com.accolite.au.mappers;

import com.accolite.au.dto.SessionDTO;
import com.accolite.au.models.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    @Mapping(target = "batch", ignore = true)
    Session toSession(SessionDTO sessionDto);

    List<SessionDTO> toSessionDTOs(List<Session> sessions);
}
