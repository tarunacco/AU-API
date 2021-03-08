package com.accolite.au.services;

import com.accolite.au.dto.AttendanceDTO;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.boot.configurationprocessor.json.JSONException;

public interface AttendanceService {

    AttendanceDTO markAndUpdateAttendanceOrMarks(AttendanceDTO attendanceDTO);

    AttendanceDTO getAttendance(int sessionId, int studentId);

    ObjectNode getAllAttendanceAndAssignmentData() throws JSONException;
}
