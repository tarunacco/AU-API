package com.accolite.au.repositories;

import com.accolite.au.embeddables.AttendanceEmbeddableId;
import com.accolite.au.models.Attendance;
import com.accolite.au.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendanceRepository  extends JpaRepository<Attendance, AttendanceEmbeddableId> {

    @Query("SELECT DISTINCT student FROM Attendance")
    List<Student> findAllDistinctStudents();

    @Query("SELECT session.sessionId, session.sessionName, status FROM Attendance WHERE student.studentId = :studentId")
    List<String[]> findAllSessionsForStudent(@Param("studentId") int studentId);
}
