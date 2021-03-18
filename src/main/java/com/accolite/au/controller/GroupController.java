package com.accolite.au.controller;

import com.accolite.au.dto.StudentGroupDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.models.Student;
import com.accolite.au.services.GroupService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping({"/add/{batchId}"})
    public ResponseEntity<StudentGroupDTO> addGroup(@Valid @RequestBody StudentGroupDTO studentGroupDTO, @PathVariable(required = true, name = "batchId") int batchId) {
        return new ResponseEntity(groupService.addGroup(studentGroupDTO, batchId), HttpStatus.CREATED);
    }

    @PostMapping({"/automate/{batchId}"})
    public ResponseEntity<List<StudentGroupDTO>> automateGrouping(@PathVariable(required = true, name = "batchId") int batchId, @RequestParam(required = true, name = "groupSize") int groupSize, @RequestBody List<Student> neglectStudentsList) {
        return new ResponseEntity(groupService.automateGrouping(batchId, neglectStudentsList, groupSize), HttpStatus.CREATED);
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<StudentGroupDTO>> getAllGroupsForABatch(@RequestParam(required = false, name = "batchId") int batchId){
        return new ResponseEntity(groupService.getAllGroupsForABatch(batchId), HttpStatus.OK);
    }

    @GetMapping({"/{groupId}"})
    public ResponseEntity<StudentGroupDTO> getGroup(@PathVariable(required = true, name = "groupId") int groupId){
        return new ResponseEntity(groupService.getGroup(groupId), HttpStatus.OK);
    }

    @DeleteMapping({"/{groupId}"})
    public ResponseEntity<SuccessResponseDTO> deleteGroup(@PathVariable(required = true, name = "groupId") int groupId){
        return new ResponseEntity(groupService.deleteGroup(groupId), HttpStatus.OK);
    }

    @DeleteMapping({"/{groupId}/{studentId}"})
    public ResponseEntity<SuccessResponseDTO> deleteStudentFromGroup(@PathVariable(required = true, name = "groupId") int groupId, @PathVariable(required = true, name = "studentId") int studentId){
        return new ResponseEntity(groupService.deleteStudentFromGroup(groupId, studentId), HttpStatus.OK);
    }

    @PutMapping({"/appendStudents/{groupId}"})
    public ResponseEntity<SuccessResponseDTO> addStudentToGroup(@PathVariable(required = true, name = "groupId") int groupId, @RequestBody List<Student> selectedStudentsList){
        return new ResponseEntity(groupService.addStudentToGroup(groupId, selectedStudentsList), HttpStatus.OK);
    }

    @PutMapping({"/{groupId}"})
    public ResponseEntity<StudentGroupDTO> updateGroup(@Valid @RequestBody StudentGroupDTO studentGroupDTO) {
        return new ResponseEntity(groupService.updateGroup(studentGroupDTO), HttpStatus.CREATED);
    }
}
