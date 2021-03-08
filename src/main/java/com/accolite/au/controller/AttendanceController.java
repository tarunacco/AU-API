package com.accolite.au.controller;

import com.accolite.au.dto.*;
import com.accolite.au.services.AttendanceService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping({"/markAttendance"})
    public ResponseEntity<AttendanceDTO> markAndUpdateAttendance(@Valid @RequestBody AttendanceDTO attendanceDTO) {
        return new ResponseEntity(attendanceService.markAndUpdateAttendanceOrMarks(attendanceDTO), HttpStatus.CREATED);
    }

    @PostMapping({"/assignMarks"})
    public ResponseEntity<AttendanceDTO> markAndUpdateMarks(@Valid @RequestBody AttendanceDTO attendanceDTO) {
        return new ResponseEntity(attendanceService.markAndUpdateAttendanceOrMarks(attendanceDTO), HttpStatus.CREATED);
    }

    @GetMapping({"/all"})
    public ResponseEntity<ObjectNode> getAllAttendanceData(){
        try{
            return new ResponseEntity(attendanceService.getAllAttendanceAndAssignmentData(), HttpStatus.OK);
        }
        catch(JSONException e){
            throw new CustomEntityNotFoundExceptionDTO("sdf");
        }
    }

    @GetMapping({"/"})
    public ResponseEntity<AttendanceDTO> getAttendanceData(@RequestParam(name = "sessionId") int sessionId, @RequestParam(name = "studentId") int studentId){
        return new ResponseEntity(attendanceService.getAttendance(sessionId, studentId), HttpStatus.OK);
    }
}
