package com.accolite.au.services.impl;

import com.accolite.au.dto.CustomEntityNotFoundExceptionDTO;
import com.accolite.au.dto.StudentDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.mappers.StudentMapper;
import com.accolite.au.models.Batch;
import com.accolite.au.models.Student;
import com.accolite.au.repositories.BatchRepository;
import com.accolite.au.repositories.StudentRepository;
import com.accolite.au.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final EntityManager entityManager;
    private final BatchRepository batchRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, EntityManager entityManager, BatchRepository batchRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.entityManager = entityManager;
        this.batchRepository = batchRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<StudentDTO> getAllStudentsForABatch(int batchId){
        if(batchRepository.existsById(batchId)) {
            return studentMapper.toStudentDTOs(studentRepository.findAllByBatch_BatchIdOOrderByFirstNameAsc(batchId));
        }
        throw new CustomEntityNotFoundExceptionDTO("Batch with id : " + batchId + " not Found");
    }

    @Override
    public StudentDTO getStudent(int studentId){
        if(studentRepository.existsById(studentId)) {
            return studentMapper.toStudentDTO(studentRepository.getOne(studentId));
        }
        throw new CustomEntityNotFoundExceptionDTO("Student with id : " + studentId + " not Found");
    }

    @Override
    public StudentDTO addStudentToBatch(StudentDTO studentDTO){
        if(batchRepository.existsById(studentDTO.getBatchId())) {
            Student student = studentMapper.toStudent(studentDTO);
            Batch batchReference = entityManager.getReference(Batch.class, studentDTO.getBatchId());
            student.setBatch(batchReference);
            return studentMapper.toStudentDTO(studentRepository.saveAndFlush(student));
        }
        throw new CustomEntityNotFoundExceptionDTO("Batch with id : " + studentDTO.getBatchId() + " not Found");
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO){
        if(studentRepository.existsById(studentDTO.getStudentId())) {
            Student student = studentMapper.toStudent(studentDTO);
            return studentMapper.toStudentDTO(studentRepository.saveAndFlush(student));
        }
        throw new CustomEntityNotFoundExceptionDTO("Student with id : " + studentDTO.getStudentId() + " not Found");
    }

    @Override
    public SuccessResponseDTO deleteStudent(int studentId){
        if(studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
            return new SuccessResponseDTO("Student with id : " + studentId + " deleted Successfully", HttpStatus.OK);
        }
        throw new CustomEntityNotFoundExceptionDTO("Student with id : " + studentId + " not Found");
    }
}
