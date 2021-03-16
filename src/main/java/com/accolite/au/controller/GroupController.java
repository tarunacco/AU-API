package com.accolite.au.controller;

import com.accolite.au.dto.StudentGroupDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.services.GroupService;
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
    public ResponseEntity<List<StudentGroupDTO>> automateGrouping(@PathVariable(required = false, name = "batchId") int batchId) {
        return new ResponseEntity(groupService.automateGrouping(batchId), HttpStatus.CREATED);
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

    @PutMapping({"/{groupId}"})
    public ResponseEntity<StudentGroupDTO> updateGroup(@Valid @RequestBody StudentGroupDTO studentGroupDTO) {
        return new ResponseEntity(groupService.updateGroup(studentGroupDTO), HttpStatus.CREATED);
    }
}
