package com.accolite.au.services;

import com.accolite.au.dto.GroupDTO;
import com.accolite.au.dto.SuccessResponseDTO;

import java.util.List;

public interface GroupService {
    GroupDTO addGroup(GroupDTO groupDTO, int batchId);

    GroupDTO updateGroup(GroupDTO groupDTO);

    GroupDTO getGroup(int batchId);

    List<GroupDTO> automateGrouping(int batchId);

    List<GroupDTO> getAllGroupsForABatch(int batchId);

    SuccessResponseDTO deleteGroup(int batchId);
}
