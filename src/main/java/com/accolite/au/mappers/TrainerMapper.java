package com.accolite.au.mappers;

import com.accolite.au.dto.TrainerDTO;
import com.accolite.au.models.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TrainerMapper {

    //@Mapping(target = "batch", ignore = true)
    @Mapping(source = "trainerDTO.businessUnit.buId", target = "businessUnit.buId")
    Trainer toTrainer(TrainerDTO trainerDTO);

//    @Mappings({
//            @Mapping(source = "businessUnit.buId", target = "businessUnitId"),
//            @Mapping(source = "batch.batchId", target = "batchId")
//    })
    
    @Mapping(source = "trainer.businessUnit", target = "businessUnit")
    TrainerDTO toTrainerDTO(Trainer trainer);

    
    List<TrainerDTO> toTrainerDTOs(List<Trainer> trainers);

    Set<TrainerDTO> toTrainerDTOsSet(Set<Trainer> trainers);

}
