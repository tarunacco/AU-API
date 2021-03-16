package com.accolite.au.services.impl;

import com.accolite.au.dto.CustomEntityNotFoundExceptionDTO;
import com.accolite.au.dto.StudentGroupDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.mappers.StudentGroupMapper;
import com.accolite.au.repositories.BatchRepository;
import com.accolite.au.repositories.GroupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements com.accolite.au.services.GroupService {
    private final GroupRepository groupRepository;
    private final StudentGroupMapper studentGroupMapper;
    private final BatchRepository batchRepository;

    public GroupServiceImpl(GroupRepository groupRepository, StudentGroupMapper studentGroupMapper, BatchRepository batchRepository) {
        this.groupRepository = groupRepository;
        this.studentGroupMapper = studentGroupMapper;
        this.batchRepository = batchRepository;
    }

    @Override
    public StudentGroupDTO addGroup(StudentGroupDTO studentGroupDTO, int batchId){
        return studentGroupDTO;
    }

    @Override
    public StudentGroupDTO updateGroup(StudentGroupDTO studentGroupDTO){
        return studentGroupDTO;
    }

    @Override
    public StudentGroupDTO getGroup(int groupId) throws CustomEntityNotFoundExceptionDTO {
        if(groupRepository.existsById(groupId)){
            return studentGroupMapper.toStudentGroupDTO(groupRepository.getOne(groupId));
        }
        throw new CustomEntityNotFoundExceptionDTO("Group Id " + groupId + " not found");
    }

    @Override
    public List<StudentGroupDTO> automateGrouping(int batchId) throws CustomEntityNotFoundExceptionDTO {
        System.out.println("Batch Id "+ batchId);
        if(batchRepository.existsById(batchId)){

        }
        else{

        }
        throw new CustomEntityNotFoundExceptionDTO("Batch Id " + batchId + " not found");
    }

    @Override
    public List<StudentGroupDTO> getAllGroupsForABatch(int batchId) throws CustomEntityNotFoundExceptionDTO {
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
