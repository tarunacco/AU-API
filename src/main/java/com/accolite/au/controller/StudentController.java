package com.accolite.au.controller;

import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.dto.StudentDTO;
import com.accolite.au.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping({"/add"})
    public ResponseEntity<StudentDTO> addStudentToBatch(@Valid @RequestBody StudentDTO studentDTO) {
        return new ResponseEntity(studentService.addStudentToBatch(studentDTO), HttpStatus.CREATED);
    }

    @PutMapping({"/update"})
    public ResponseEntity<StudentDTO> updateStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return new ResponseEntity(studentService.updateStudent(studentDTO), HttpStatus.CREATED);
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<StudentDTO>> getAllStudentsForABatch(@RequestParam(required = true, name = "batchId") int batchId){
        return new ResponseEntity(studentService.getAllStudentsForABatch(batchId), HttpStatus.OK);
    }

    @GetMapping({"/{studentId}"})
    public ResponseEntity<StudentDTO> getStudent(@PathVariable(required = true, name = "studentId") int studentId){
        return new ResponseEntity(studentService.getStudent(studentId), HttpStatus.OK);
    }

    @DeleteMapping({"/{studentId}"})
    public ResponseEntity<SuccessResponseDTO> deleteStudent(@PathVariable(required = true, name="studentId") int studentId){
        return new ResponseEntity(studentService.deleteStudent(studentId), HttpStatus.OK);
    }
}
