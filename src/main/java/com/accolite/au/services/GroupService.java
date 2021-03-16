package com.accolite.au.services;

import com.accolite.au.dto.StudentGroupDTO;
import com.accolite.au.dto.SuccessResponseDTO;

import java.util.List;

public interface GroupService {
    StudentGroupDTO addGroup(StudentGroupDTO studentGroupDTO, int batchId);

    StudentGroupDTO updateGroup(StudentGroupDTO studentGroupDTO);

    StudentGroupDTO getGroup(int batchId);

    List<StudentGroupDTO> automateGrouping(int batchId);

    List<StudentGroupDTO> getAllGroupsForABatch(int batchId);

    SuccessResponseDTO deleteGroup(int batchId);
}
