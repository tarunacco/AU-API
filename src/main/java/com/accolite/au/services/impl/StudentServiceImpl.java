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
import com.accolite.au.utils.ValidatorFunctions;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final EntityManager entityManager;
    private final BatchRepository batchRepository;
    private final StudentMapper studentMapper;
    private final ValidatorFunctions validatorFunctions;

    public StudentServiceImpl(StudentRepository studentRepository, EntityManager entityManager, BatchRepository batchRepository, StudentMapper studentMapper, ValidatorFunctions validatorFunctions) {
        this.studentRepository = studentRepository;
        this.entityManager = entityManager;
        this.batchRepository = batchRepository;
        this.studentMapper = studentMapper;
        this.validatorFunctions = validatorFunctions;
    }

    @Override
    public List<StudentDTO> getAllStudentsForABatch(int batchId){
        if(batchRepository.existsById(batchId)) {
            return studentMapper.toStudentDTOs(studentRepository.findAllByBatch_BatchIdOrderByFirstNameAsc(batchId));
        }
        throw new CustomEntityNotFoundExceptionDTO("Batch with id : " + batchId + " not Found");
    }

    @Override
    public List<StudentDTO> getAllUnassignedStudentsForABatch(int batchId){
        if(batchRepository.existsById(batchId)) {
            return studentMapper.toStudentDTOs(studentRepository.findAllByBatch_BatchIdWhereStudentGroupIsNull(batchId));
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
    public StudentDTO addOrUpdateStudentToBatch(StudentDTO studentDTO){
        if(batchRepository.existsById(studentDTO.getBatchId())) {
            return this.saveStudentEntry(studentDTO, studentDTO.getBatchId());
        }
        throw new CustomEntityNotFoundExceptionDTO("Batch with id : " + studentDTO.getBatchId() + " not Found");
    }

    @Override
    public SuccessResponseDTO deleteStudent(int studentId){
        if(studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
            return new SuccessResponseDTO("Student with id : " + studentId + " deleted Successfully", HttpStatus.OK);
        }
        throw new CustomEntityNotFoundExceptionDTO("Student with id : " + studentId + " not Found");
    }

    private StudentDTO saveStudentEntry(StudentDTO studentDTO, int batchId){
        Student student = studentMapper.toStudent(studentDTO);
        Batch batchReference = entityManager.getReference(Batch.class, batchId);
        student.setBatch(batchReference);
        return studentMapper.toStudentDTO(studentRepository.saveAndFlush(student));
    }

    private boolean isValidRow(String[] row){
        for(int i = 0 ; i < row.length - 1 ; i++){
            if(row[i].compareTo("") == 0){
                return false;
            }
            if(i == row.length - 2){
                if(validatorFunctions.emailValidator(row[i]) == false){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void uploadFile(MultipartFile studentFile, int batchId) {
        try{
            InputStreamReader reader = new InputStreamReader(studentFile.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            List<String[]> rows = csvReader.readAll();
            for(String[] row : rows){
                if(this.isValidRow(row)){
                    this.saveStudentEntry(new StudentDTO(row[0], row[1], row[2], row[3]), batchId);
                }
                else{
                    System.out.println("Validation Error, Wrong Row Format");
                }
            }
        } catch (Exception e){
            System.out.println("Some Exception Occured!! " + e.toString());
        }
    }

    @Override
    public List<Map<String, ?>> getAllStudentsCountPerLocation(int batchId){
        return studentRepository.getAllStudentsCountPerLocation(batchId);
    }
}
