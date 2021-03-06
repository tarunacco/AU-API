package com.accolite.au.mappers;

import com.accolite.au.dto.SessionDTO;
import com.accolite.au.models.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    @Mappings({
            @Mapping(target = "batch", ignore = true),
            @Mapping(source = "sessionDto.batchId", target = "batch.batchId")
            //@Mapping(source = "sessionDto.trainerId", target = "trainer.trainerId")
    })
    Session toSession(SessionDTO sessionDto);

    @Mappings({
            @Mapping(source = "batch.batchId", target = "batchId")
            //@Mapping(source = "trainer.trainerId", target = "trainerId")
    })
    SessionDTO toSessionDTO(Session session);

    List<SessionDTO> toSessionDTOs(List<Session> sessions);
}
