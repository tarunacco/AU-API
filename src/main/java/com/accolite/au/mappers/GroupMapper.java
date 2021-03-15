package com.accolite.au.mappers;

import com.accolite.au.dto.GroupDTO;
import com.accolite.au.models.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mappings({
            @Mapping(source = "trainerDTO.trainerId", target = "trainer.trainerId"),
    })
    Group toGroup(GroupDTO groupDTO);

    GroupDTO toGroupDTO(Group group);

    List<GroupDTO> toSessionDTOs(List<Group> groups);
}