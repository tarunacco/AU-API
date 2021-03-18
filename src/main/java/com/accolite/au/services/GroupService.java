package com.accolite.au.services;

import com.accolite.au.dto.CustomEntityNotFoundExceptionDTO;
import com.accolite.au.dto.StudentGroupDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.models.Student;

import java.util.List;

public interface GroupService {
    StudentGroupDTO addGroup(StudentGroupDTO studentGroupDTO, int batchId);

    StudentGroupDTO updateGroup(StudentGroupDTO studentGroupDTO);

    StudentGroupDTO getGroup(int batchId);

    List<StudentGroupDTO> automateGrouping(int batchId, List<Student> neglectStudentsList, int groupSize) throws CustomEntityNotFoundExceptionDTO;

    List<StudentGroupDTO> getAllGroupsForABatch(int batchId);

    SuccessResponseDTO deleteGroup(int batchId);

    SuccessResponseDTO deleteStudentFromGroup(int groupId, int studentId) throws CustomEntityNotFoundExceptionDTO;

    StudentGroupDTO addStudentToGroup(int groupId, List<Student> selectedStudentsList) throws CustomEntityNotFoundExceptionDTO;
}
