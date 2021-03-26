package com.accolite.au.services.impl;

import com.accolite.au.dto.*;
import com.accolite.au.mappers.ProjectFeedbackMapper;
import com.accolite.au.mappers.StudentGroupMapper;
import com.accolite.au.mappers.StudentMapper;
import com.accolite.au.models.*;
import com.accolite.au.repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GroupServiceImpl implements com.accolite.au.services.GroupService {
    private final GroupRepository groupRepository;
    private final StudentGroupMapper studentGroupMapper;
    private final BatchRepository batchRepository;
    private final StudentRepository studentRepository;
    private final TrainerRepository trainerRepository;
    private final StudentMapper studentMapper;
    private final EntityManager entityManager;
    private final ProjectFeedbackRepository projectFeedbackRepository;
    private final ProjectFeedbackMapper projectFeedbackMapper;
    private final SessionRepository sessionRepository;
    private final TrainingRepository trainingRepository;

    public GroupServiceImpl(GroupRepository groupRepository, StudentGroupMapper studentGroupMapper, BatchRepository batchRepository, StudentRepository studentRepository, TrainerRepository trainerRepository, StudentMapper studentMapper, EntityManager entityManager, ProjectFeedbackRepository projectFeedbackRepository, ProjectFeedbackMapper projectFeedbackMapper, SessionRepository sessionRepository, TrainingRepository trainingRepository) {
        this.groupRepository = groupRepository;
        this.studentGroupMapper = studentGroupMapper;
        this.batchRepository = batchRepository;
        this.studentRepository = studentRepository;
        this.trainerRepository = trainerRepository;
        this.studentMapper = studentMapper;
        this.entityManager = entityManager;
        this.projectFeedbackRepository = projectFeedbackRepository;
        this.projectFeedbackMapper = projectFeedbackMapper;
        this.sessionRepository = sessionRepository;
        this.trainingRepository = trainingRepository;
    }

    @Override
    public StudentGroupDTO addGroup(StudentGroupDTO studentGroupDTO, int batchId) throws CustomEntityNotFoundExceptionDTO {
        if(batchRepository.existsById(batchId)) {
            System.out.println(studentGroupDTO.getStudents().size());
            StudentGroup studentGroup = studentGroupMapper.toStudentGroup(studentGroupDTO);
            Trainer trainer = entityManager.getReference(Trainer.class, studentGroupDTO.getTrainerId());
            studentGroup.setBatch(entityManager.getReference(Batch.class, batchId));
            studentGroup.setTrainer(trainer);
            System.out.println(studentGroup.getStudents().size());
            studentGroup = groupRepository.saveAndFlush(studentGroup);
            System.out.println(studentGroup.getStudents().size());
            for(Student student : studentGroup.getStudents()){
                student.setStudentGroup(studentGroup);
                studentRepository.saveAndFlush(student);
            }
            return studentGroupMapper.toStudentGroupDTO(studentGroup);
        }
        throw new CustomEntityNotFoundExceptionDTO("Batch Id " + batchId + " not found");
    }

    @Override
    public StudentGroupDTO updateGroup(StudentGroupDTO studentGroupDTO, int groupId){
        if(groupRepository.existsById(groupId)){
            StudentGroup studentGroup = groupRepository.getOne(groupId);
            studentGroup.setProjectDocUrl(studentGroupDTO.getProjectDocUrl());
            studentGroup.setProjectName(studentGroupDTO.getProjectName());
            studentGroup.setStudentGroupName(studentGroupDTO.getStudentGroupName());
            studentGroup.setGroupFeedback(studentGroupDTO.getGroupFeedback());
            Trainer trainer = entityManager.getReference(Trainer.class, studentGroupDTO.getTrainerId());
            studentGroup.setTrainer(trainer);
            return studentGroupMapper.toStudentGroupDTO(groupRepository.saveAndFlush(studentGroup));
        }
        throw new CustomEntityNotFoundExceptionDTO("Group Id " + groupId + " not found");
    }

    @Override
    public StudentGroupDTO getGroup(int groupId) throws CustomEntityNotFoundExceptionDTO {
        if(groupRepository.existsById(groupId)){
            return studentGroupMapper.toStudentGroupDTO(groupRepository.getOne(groupId));
        }
        throw new CustomEntityNotFoundExceptionDTO("Group Id " + groupId + " not found");
    }

    @Override
    public List<StudentGroupDTO> automateGrouping(int batchId, List<Student> neglectStudentsList, int groupSize) throws CustomEntityNotFoundExceptionDTO {
        if(batchRepository.existsById(batchId)){
            List<Student> studentList = studentRepository.findAllByStudentInStudentsList(neglectStudentsList);
            List<Trainer> trainerList = trainerRepository.findAll();

            // Total Groups To Be Formed
            int totalGroups = studentList.size() / groupSize;
            if(studentList.size() % groupSize != 0){
                totalGroups += 1;
            }

            // Student's grouping
            int startOfChunk = 0;
            int endOfChunk = groupSize;

            int trainerAssignCounter = 0;
            int groupCounter = groupRepository.findAllByBatch_BatchId(batchId).size() + 1;

            for(int i = 0 ; i < totalGroups ; i++){
                if(trainerAssignCounter >= trainerList.size()){
                    trainerAssignCounter = 0;
                }
                String groupName = "Batch " + batchId + " - Group " + (groupCounter++);
                try{
                    this.addGroup(new StudentGroupDTO(batchId, new HashSet(studentList.subList(startOfChunk, Math.min(studentList.size(), endOfChunk))), groupName, trainerList.get(trainerAssignCounter++).getTrainerId()), batchId);
                }
                catch (CustomEntityNotFoundExceptionDTO e){
                    // this exception can only arise when group deleted by some other person simultaneously.
                }
                startOfChunk = endOfChunk;
                endOfChunk += groupSize;
            }
            return this.getAllGroupsForABatch(batchId);
        }
        throw new CustomEntityNotFoundExceptionDTO("Batch Id " + batchId + " not found");
    }

    @Override
    public List<StudentGroupDTO> getAllGroupsForABatch(int batchId) throws CustomEntityNotFoundExceptionDTO {
        if(batchRepository.existsById(batchId)){
            return studentGroupMapper.toStudentGroupDTOs(groupRepository.findAllByBatch_BatchId(batchId));
        }
        throw new CustomEntityNotFoundExceptionDTO("Batch Id " + batchId + " not found");
    }


    @Override
    public SuccessResponseDTO deleteGroup(int groupId) throws CustomEntityNotFoundExceptionDTO {
        if(groupRepository.existsById(groupId)){
            groupRepository.deleteById(groupId);
            return new SuccessResponseDTO("Successfully deleted Group having id " + groupId, HttpStatus.OK);
        }
        throw new CustomEntityNotFoundExceptionDTO("Group Id " + groupId + " not found");
    }

    @Override
    public SuccessResponseDTO deleteStudentFromGroup(int groupId, int studentId) throws CustomEntityNotFoundExceptionDTO {
        if(groupRepository.existsById(groupId) && studentRepository.existsById(studentId)){
            if(studentRepository.getOne(studentId).getStudentGroup() != null &&
                    studentRepository.getOne(studentId).getStudentGroup().getStudentGroupId() == groupId) {
                
                StudentGroup studentGroup = groupRepository.getOne(groupId);
                Student student = studentRepository.getOne(studentId);
                studentGroup.getStudents().remove(student);
                groupRepository.saveAndFlush(studentGroup);
                student.setStudentGroup(null);
                studentRepository.saveAndFlush(student);
                return new SuccessResponseDTO("Successfully deleted Group having id " + groupId, HttpStatus.OK);
            }
            throw new CustomEntityNotFoundExceptionDTO("No Student With Id : " + studentId + " Present In This Group With Id : " + groupId + " not found");
        }
        throw new CustomEntityNotFoundExceptionDTO("Group Id " + groupId + " not found");
    }

    @Override
    public StudentGroupDTO addStudentToGroup(int groupId, List<Student> selectedStudentsList) throws CustomEntityNotFoundExceptionDTO {
        if(groupRepository.existsById(groupId)){
            StudentGroup studentGroup = groupRepository.getOne(groupId);
            studentGroup.getStudents().addAll(selectedStudentsList);
            StudentGroupDTO studentGroupDTO = studentGroupMapper.toStudentGroupDTO(studentGroup);
            studentGroupDTO.setStudents(studentGroup.getStudents());
            return this.addGroup(studentGroupDTO, studentGroup.getBatch().getBatchId());
        }
        throw new CustomEntityNotFoundExceptionDTO("Group Id " + groupId + " not found");
    }

    public boolean isValid(Field obj, ProjectFeedback obj1) throws IllegalAccessException {
        if(obj.get(obj1) == null){
            return false;
        }
        else if(obj.get(obj1) instanceof java.lang.Integer){
            if(((Integer) obj.get(obj1)).intValue() == 0){
                return false;
            }
        }
        else if(obj.get(obj1) instanceof java.lang.String){
            if(((String) obj.get(obj1)).compareTo("") == 0){
                return false;
            }
        }
        return true;
    }

    public ProjectFeedback updateProjectFeedback(ProjectFeedback oldObject, ProjectFeedback newObject) {

        for (Field field : oldObject.getClass().getDeclaredFields()) {
            for (Field newField : newObject.getClass().getDeclaredFields()) {
                if (field.getName().equals(newField.getName())) {
                    try {
                        field.setAccessible(true);
                        newField.setAccessible(true);
                        System.out.println(field.getName()+" :- "+field.get(oldObject)+" "+newField.get(newObject)+" "+field.get(oldObject).getClass()+" "+newField.get(newObject).getClass());
                        ConcurrentHashMap<Object, Object> concHashMap = new ConcurrentHashMap<Object, Object>();
                        Object newVal = isValid(newField, newObject) == false
                                ? field.get(oldObject)
                                : newField.get(newObject);
                        concHashMap.put(newField.getName(), newVal);

                        field.set(
                                this,
                                concHashMap);
                        //System.out.println(field);

                    } catch (IllegalAccessException ignore) {
                        // Field update exception on final modifier and other cases.
                        //System.out.println(ignore.getMessage());
                    }
                }
            }
        }
        return oldObject;
    }

    @Override
    public FeedbackDTO submitFeedback(FeedbackDTO feedbackDTO, int groupId){
        if(groupRepository.existsById(groupId)){
            StudentGroup studentGroup = groupRepository.getOne(groupId);
            studentGroup.setGroupFeedback(feedbackDTO.getGroupFeedback());

            for(ProjectFeedback projectFeedback: projectFeedbackMapper.toProjectFeedbackSet(feedbackDTO.getProjectFeedbackList())){
                System.out.println(projectFeedback.toString());
            }
            Set<ProjectFeedback> finalFeedbacksSet = new HashSet<>();
            for(ProjectFeedback projectFeedback: projectFeedbackMapper.toProjectFeedbackSet(feedbackDTO.getProjectFeedbackList())){
                ProjectFeedback projectFeedback1 = projectFeedbackRepository.containsStudentFeedback(projectFeedback.getStudent().getStudentId());
                if(projectFeedback1 != null){
                    finalFeedbacksSet.add(projectFeedback1);
                    //finalFeedbacksSet.add(this.updateProjectFeedback(projectFeedback1, projectFeedback));
                }
                else{
                    finalFeedbacksSet.add(projectFeedback);
                }
            }
            for(ProjectFeedback projectFeedback: finalFeedbacksSet){
                System.out.println(projectFeedback.toString());
            }
            studentGroup.setProjectFeedbacks(finalFeedbacksSet);

            for(ProjectFeedback projectFeedback: finalFeedbacksSet) {
                Student student = studentRepository.getOne(projectFeedback.getStudent().getStudentId());
                student.setProjectFeedback(projectFeedback);
                studentRepository.saveAndFlush(student);
            }
            FeedbackDTO myFeedback = new FeedbackDTO(groupId, studentGroup.getGroupFeedback(), projectFeedbackMapper.toProjectFeedbackDTOsSet(finalFeedbacksSet));
            groupRepository.saveAndFlush(studentGroup);
            //return this.getAllFeedbacks(groupId);
            return myFeedback;
        }
        throw new CustomEntityNotFoundExceptionDTO("Group Id " + groupId + " not found");
    }

    @Override
    public FeedbackDTO getAllFeedbacks(int groupId){
        if(groupRepository.existsById(groupId)) {
            StudentGroup studentGroup = groupRepository.getOne(groupId);
            return new FeedbackDTO(groupId, studentGroup.getGroupFeedback(), projectFeedbackMapper.toProjectFeedbackDTOsSet(studentGroup.getProjectFeedbacks()));
        }
        throw new CustomEntityNotFoundExceptionDTO("Group Id " + groupId + " not found");
    }

    @Override
    public ProjectFeedbackDTO getFeedback(int feedbackId) {
        if(projectFeedbackRepository.existsById(feedbackId)) {
            return projectFeedbackMapper.toProjectFeedbackDTO(projectFeedbackRepository.getOne(feedbackId));
        }
        throw new CustomEntityNotFoundExceptionDTO("Feedback Id " + feedbackId + " not found");
    }

    @Override
    public ObjectNode getAllFinalEvaluationData(int batchId){
        // Object Mapper
        ObjectMapper mapper = new ObjectMapper();

        // create a ObjectNode root Node
        ObjectNode rootNode = mapper.createObjectNode();

        List<String[]> sessionsReport ;

        //sessionsReport = sessionRepository.findAllSessions();
        sessionsReport = sessionRepository.findAllSessionsByBatchId(batchId);

        ArrayNode sessionNode = mapper.createArrayNode();

        for(String[] row: sessionsReport){

            ObjectNode tempSessionEntity = mapper.createObjectNode();
            tempSessionEntity.put("sessionId", row[1]);
            tempSessionEntity.put("sessionName", row[0]);
            sessionNode.add(tempSessionEntity);
        }

        rootNode.set("sessions", sessionNode);

        ArrayNode attendanceDataNode = mapper.createArrayNode();

        for (Student student : studentRepository.findAllByBatch_BatchIdOrderByFirstNameAsc(batchId)) {
            List<String[]> tempSessions = trainingRepository.findAllSessionsForStudent(student.getStudentId()); // [['session1', 'A', 12], ['session2', 'P', 45]]

            ObjectNode tempEntity = mapper.createObjectNode();

            // Creating Student JSONObject
            ObjectNode tempStudentEntity = mapper.createObjectNode();
            tempStudentEntity.put("studentName", student.getFirstName() + " " + student.getLastName());
            tempStudentEntity.put("studentEmail", student.getEmailId());
            tempStudentEntity.put("studentFirstName", student.getFirstName());
            tempStudentEntity.put("studentLastName", student.getLastName());
            tempStudentEntity.put("studentId", student.getStudentId());
            tempEntity.put("student", tempStudentEntity.toString());

            tempEntity.set("student", tempStudentEntity);
            ProjectFeedback projectFeedback = projectFeedbackRepository.containsStudentFeedback(student.getStudentId());

            ObjectNode projectTempEntity = mapper.createObjectNode();
            projectTempEntity.put("feedbackId", projectFeedback.getFeedbackId());
            projectTempEntity.put("marks", projectFeedback.getMarks());
            projectTempEntity.put("feedback", projectFeedback.getFeedback());

            tempEntity.set("projectDetails", projectTempEntity);

            for (String[] row : tempSessions) {
                ObjectNode tempSessionEntity = mapper.createObjectNode();
                tempSessionEntity.put("sessionName", row[1]);
                tempSessionEntity.put("marks", row[3]);
                tempEntity.set(row[0], tempSessionEntity);
            }

            attendanceDataNode.add(tempEntity);
        }

        rootNode.set("marksData", attendanceDataNode);

        return rootNode;
    }

    @Override
    public ProjectFeedbackDTO assignMarks(ProjectFeedbackDTO projectFeedbackDTO){
        if (projectFeedbackRepository.existsById(projectFeedbackDTO.getFeedbackId())) {
            ProjectFeedback projectFeedback = projectFeedbackRepository.getOne(projectFeedbackDTO.getFeedbackId());
            projectFeedback.setMarks(projectFeedbackDTO.getMarks());
            return projectFeedbackMapper.toProjectFeedbackDTO(projectFeedbackRepository.saveAndFlush(projectFeedback));
        }
        throw new CustomEntityNotFoundExceptionDTO("Feedback Id " + projectFeedbackDTO.getFeedbackId() + " not found");
    }
}
