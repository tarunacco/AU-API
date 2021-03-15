package com.accolite.au.services.impl;

import com.accolite.au.dto.CustomEntityNotFoundExceptionDTO;
import com.accolite.au.dto.GroupDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.mappers.GroupMapper;
import com.accolite.au.repositories.BatchRepository;
import com.accolite.au.repositories.GroupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements com.accolite.au.services.GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final BatchRepository batchRepository;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper, BatchRepository batchRepository) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.batchRepository = batchRepository;
    }

    @Override
    public GroupDTO addGroup(GroupDTO groupDTO, int batchId){
        return groupDTO;
    }

    @Override
    public GroupDTO updateGroup(GroupDTO groupDTO){
        return groupDTO;
    }

    @Override
    public GroupDTO getGroup(int groupId) throws CustomEntityNotFoundExceptionDTO {
        if(groupRepository.existsById(groupId)){
            return groupMapper.toGroupDTO(groupRepository.getOne(groupId));
        }
        throw new CustomEntityNotFoundExceptionDTO("Group Id " + groupId + " not found");
    }

    @Override
    public List<GroupDTO> automateGrouping(int batchId) throws CustomEntityNotFoundExceptionDTO {
        System.out.println("Batch Id "+ batchId);
        if(batchRepository.existsById(batchId)){

        }
        else{

        }
        throw new CustomEntityNotFoundExceptionDTO("Batch Id " + batchId + " not found");
    }

    @Override
    public List<GroupDTO> getAllGroupsForABatch(int batchId) throws CustomEntityNotFoundExceptionDTO {
        if(batchRepository.existsById(batchId)){

        }
        throw new CustomEntityNotFoundExceptionDTO("Batch Id " + batchId + " not found");
    }

    @Override
    public SuccessResponseDTO deleteGroup(int groupId) throws CustomEntityNotFoundExceptionDTO {
        if(groupRepository.existsById(groupId)){
            return new SuccessResponseDTO("", HttpStatus.OK);
        }
        throw new CustomEntityNotFoundExceptionDTO("Group Id " + groupId + " not found");
    }
}
