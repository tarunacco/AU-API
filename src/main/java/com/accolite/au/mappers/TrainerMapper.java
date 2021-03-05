package com.accolite.au.mappers;

import com.accolite.au.dto.TrainerDTO;
import com.accolite.au.models.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrainerMapper {

    @Mappings({@Mapping(target = "batch", ignore = true)})
    Trainer toTrainer(TrainerDTO trainerDTO);

    TrainerDTO toTrainerDTO(Trainer trainer);

    List<TrainerDTO> toTrainerDTOs(List<Trainer> trainers);

}
