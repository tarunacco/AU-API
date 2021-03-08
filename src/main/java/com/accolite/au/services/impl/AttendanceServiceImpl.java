package com.accolite.au.services.impl;

import com.accolite.au.dto.AttendanceDTO;
import com.accolite.au.dto.CustomEntityNotFoundExceptionDTO;
import com.accolite.au.embeddables.AttendanceEmbeddableId;
import com.accolite.au.mappers.AttendanceMapper;
import com.accolite.au.models.Attendance;
import com.accolite.au.models.Session;
import com.accolite.au.models.Student;
import com.accolite.au.repositories.AttendanceRepository;
import com.accolite.au.repositories.SessionRepository;
import com.accolite.au.repositories.StudentRepository;
import com.accolite.au.services.AttendanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final SessionRepository sessionRepository;
    private final EntityManager entityManager;
    private final StudentRepository studentRepository;
    private final AttendanceMapper attendanceMapper;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, SessionRepository sessionRepository, EntityManager entityManager, StudentRepository studentRepository, AttendanceMapper attendanceMapper) {
        this.attendanceRepository = attendanceRepository;
        this.sessionRepository = sessionRepository;
        this.entityManager = entityManager;
        this.studentRepository = studentRepository;
        this.attendanceMapper = attendanceMapper;
    }

    @Override
    public AttendanceDTO markAndUpdateAttendanceOrMarks(AttendanceDTO attendanceDTO){
        if(sessionRepository.existsById(attendanceDTO.getAttendanceId().getSessionId()) && studentRepository.existsById(attendanceDTO.getAttendanceId().getStudentId())){
            Attendance attendance = attendanceMapper.toAttendance(attendanceDTO);
            Student student = entityManager.getReference(Student.class, attendanceDTO.getAttendanceId().getStudentId());
            Session session = entityManager.getReference(Session.class, attendanceDTO.getAttendanceId().getSessionId());
            attendance.setSession(session);
            attendance.setStudent(student);
            return attendanceMapper.toAttendanceDTO(attendanceRepository.saveAndFlush(attendance));
        }
        else if(!sessionRepository.existsById(attendanceDTO.getAttendanceId().getSessionId())){
            throw new CustomEntityNotFoundExceptionDTO("Session with id : " + attendanceDTO.getAttendanceId().getSessionId() + " not Found");
        }
        throw new CustomEntityNotFoundExceptionDTO("Student with id : " + attendanceDTO.getAttendanceId().getStudentId() + " not Found");
    }

    @Override
    public AttendanceDTO getAttendance(int sessionId, int studentId){
        if(sessionRepository.existsById(sessionId) && studentRepository.existsById(studentId)){
            return attendanceMapper.toAttendanceDTO(attendanceRepository.getOne(new AttendanceEmbeddableId(sessionId, studentId)));
        }
        else if(!sessionRepository.existsById(sessionId)){
            throw new CustomEntityNotFoundExceptionDTO("Session with id : " + sessionId + " not Found");
        }
        throw new CustomEntityNotFoundExceptionDTO("Student with id : " + studentId + " not Found");
    }

    @Override
    public ObjectNode getAllAttendanceAndAssignmentData(){
        // GENERATING CUSTOM ATTENDANCE RESPONSE

        // Object Mapper
        ObjectMapper mapper = new ObjectMapper();

        // create a ObjectNode root Node
        ObjectNode rootNode = mapper.createObjectNode();

        List<String[]> sessions = sessionRepository.findAllSessions(); // [['session1', 1], ['session2', 2]]

        ArrayNode sessionNode = mapper.createArrayNode();

        for(String[] row: sessions){

            ObjectNode tempSessionEntity = mapper.createObjectNode();
            tempSessionEntity.put("sessionId", row[1]);
            tempSessionEntity.put("sessionName", row[0]);

            sessionNode.add(tempSessionEntity);
        }

        rootNode.set("sessions", sessionNode);

        ArrayNode attendanceDataNode = mapper.createArrayNode();

        for (Student student : attendanceRepository.findAllDistinctStudents()) {
            List<String[]> tempSessions = attendanceRepository.findAllSessionsForStudent(student.getStudentId()); // [['session1', 'A'], ['session2', 'P']]

            ObjectNode tempEntity = mapper.createObjectNode();

            // Creating Student JSONObject
            ObjectNode tempStudentEntity = mapper.createObjectNode();
            tempStudentEntity.put("studentName", student.getFirstName() + " " + student.getLastName());
            tempStudentEntity.put("studentId", student.getStudentId());
            tempEntity.put("student", tempStudentEntity.toString());

            tempEntity.set("student", tempStudentEntity);

            for (String[] row : tempSessions) {
                ObjectNode tempSessionEntity = mapper.createObjectNode();
                tempSessionEntity.put("sessionName", row[1]);
                tempSessionEntity.put("attendance", row[2]);
                tempEntity.set(row[0], tempSessionEntity);
            }

            attendanceDataNode.add(tempEntity);
        }

        rootNode.set("attendanceData", attendanceDataNode);

        return rootNode;
    }

}
