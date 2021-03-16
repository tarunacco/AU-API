package com.accolite.au.mappers;

import com.accolite.au.dto.StudentGroupDTO;
import com.accolite.au.models.StudentGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentGroupMapper {

    @Mappings({
            @Mapping(source = "trainerId", target = "trainer.trainerId"),
            @Mapping(source = "batchId", target = "batch.batchId")
    })
    StudentGroup toStudentGroup(StudentGroupDTO studentGroupDTO);

    StudentGroupDTO toStudentGroupDTO(StudentGroup group);

    List<StudentGroupDTO> toStudentGroupDTOs(List<StudentGroup> groups);
}