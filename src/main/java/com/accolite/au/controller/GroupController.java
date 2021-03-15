package com.accolite.au.controller;

import com.accolite.au.dto.GroupDTO;
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
    public ResponseEntity<GroupDTO> addGroup(@Valid @RequestBody GroupDTO groupDTO, @PathVariable(required = true, name = "batchId") int batchId) {
        return new ResponseEntity(groupService.addGroup(groupDTO, batchId), HttpStatus.CREATED);
    }

    @PostMapping({"/automate/{batchId}"})
    public ResponseEntity<List<GroupDTO>> automateGrouping(@PathVariable(required = false, name = "batchId") int batchId) {
        return new ResponseEntity(groupService.automateGrouping(batchId), HttpStatus.CREATED);
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<GroupDTO>> getAllGroupsForABatch(@RequestParam(required = false, name = "batchId") int batchId){
        return new ResponseEntity(groupService.getAllGroupsForABatch(batchId), HttpStatus.OK);
    }

    @GetMapping({"/{groupId}"})
    public ResponseEntity<GroupDTO> getGroup(@PathVariable(required = true, name = "groupId") int groupId){
        return new ResponseEntity(groupService.getGroup(groupId), HttpStatus.OK);
    }

    @DeleteMapping({"/{groupId}"})
    public ResponseEntity<SuccessResponseDTO> deleteGroup(@PathVariable(required = true, name = "groupId") int groupId){
        return new ResponseEntity(groupService.deleteGroup(groupId), HttpStatus.OK);
    }

    @PutMapping({"/{groupId}"})
    public ResponseEntity<GroupDTO> updateGroup(@Valid @RequestBody GroupDTO groupDTO) {
        return new ResponseEntity(groupService.updateGroup(groupDTO), HttpStatus.CREATED);
    }
}
