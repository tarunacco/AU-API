package com.accolite.au.mappers;

import com.accolite.au.dto.AttendanceDTO;
import com.accolite.au.models.Attendance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {
    Attendance toAttendance(AttendanceDTO attendanceDTO);

    AttendanceDTO toAttendanceDTO(Attendance attendance);
}
