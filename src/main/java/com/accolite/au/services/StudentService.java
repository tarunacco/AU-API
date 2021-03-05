package com.accolite.au.services;

import com.accolite.au.dto.StudentDTO;
import com.accolite.au.dto.SuccessResponseDTO;

import java.util.List;

public interface StudentService {

    List<StudentDTO> getAllStudentsForABatch(int batchId);

    StudentDTO getStudent(int studentId);

    StudentDTO addStudentToBatch(StudentDTO studentDTO);

    StudentDTO updateStudent(StudentDTO studentDTO);

    SuccessResponseDTO deleteStudent(int studentId);
}
