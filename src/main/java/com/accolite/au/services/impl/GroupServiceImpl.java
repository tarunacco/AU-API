package com.accolite.au.services.impl;

import com.accolite.au.dto.CustomEntityNotFoundExceptionDTO;
import com.accolite.au.dto.StudentDTO;
import com.accolite.au.dto.StudentGroupDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.mappers.StudentGroupMapper;
import com.accolite.au.mappers.StudentMapper;
import com.accolite.au.models.Batch;
import com.accolite.au.models.Student;
import com.accolite.au.models.StudentGroup;
import com.accolite.au.models.Trainer;
import com.accolite.au.repositories.BatchRepository;
import com.accolite.au.repositories.GroupRepository;
import com.accolite.au.repositories.StudentRepository;
import com.accolite.au.repositories.TrainerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GroupServiceImpl implements com.accolite.au.services.GroupService {
    private final GroupRepository groupRepository;
    private final StudentGroupMapper studentGroupMapper;
    private final BatchRepository batchRepository;
    private final StudentRepository studentRepository;
    private final TrainerRepository trainerRepository;
    private final StudentMapper studentMapper;
    private final EntityManager entityManager;

    public GroupServiceImpl(GroupRepository groupRepository, StudentGroupMapper studentGroupMapper, BatchRepository batchRepository, StudentRepository studentRepository, TrainerRepository trainerRepository, StudentMapper studentMapper, EntityManager entityManager) {
        this.groupRepository = groupRepository;
        this.studentGroupMapper = studentGroupMapper;
        this.batchRepository = batchRepository;
        this.studentRepository = studentRepository;
        this.trainerRepository = trainerRepository;
        this.studentMapper = studentMapper;
        this.entityManager = entityManager;
    }

    @Override
    public StudentGroupDTO addGroup(StudentGroupDTO studentGroupDTO, int batchId) throws CustomEntityNotFoundExceptionDTO {
        if(batchRepository.existsById(batchId)) {
            StudentGroup studentGroup = studentGroupMapper.toStudentGroup(studentGroupDTO);
            Trainer trainer = entityManager.getReference(Trainer.class, studentGroupDTO.getTrainerId());
            studentGroup.setBatch(entityManager.getReference(Batch.class, batchId));
            studentGroup.setTrainer(trainer);
            groupRepository.saveAndFlush(studentGroup);
            for(Student student : studentGroup.getStudents()){
                student.setStudentGroup(studentGroup);
            }
            return studentGroupMapper.toStudentGroupDTO(studentGroup);
        }
        throw new CustomEntityNotFoundExceptionDTO("Batch Id " + batchId + " not found");
    }

    @Override
    public StudentGroupDTO updateGroup(StudentGroupDTO studentGroupDTO){
        return studentGroupDTO;
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
            if(studentRepository.getOne(studentId).getStudentGroup() != null && studentRepository.getOne(studentId).getStudentGroup().getStudentGroupId() == groupId) {
                StudentGroup studentGroup = groupRepository.getOne(groupId);
                studentGroup.getStudents().remove(studentRepository.getOne(studentId));
                Student student = studentRepository.getOne(studentId);
                student.setStudentGroup(null);
                groupRepository.saveAndFlush(studentGroup);
                studentRepository.saveAndFlush(student);
                return new SuccessResponseDTO("Successfully deleted Group having id " + groupId, HttpStatus.OK);
            }
            throw new CustomEntityNotFoundExceptionDTO("No Student With Id : " + studentId + " Present In This Group With Id : " + groupId + " not found");
        }
        throw new CustomEntityNotFoundExceptionDTO("Group Id " + groupId + " not found");
    }

    @Override
    public SuccessResponseDTO addStudentToGroup(int groupId, List<Student> selectedStudentsList) throws CustomEntityNotFoundExceptionDTO {
        if(groupRepository.existsById(groupId)){
            return new SuccessResponseDTO("Successfully deleted Group having id " + groupId, HttpStatus.OK);
        }
        throw new CustomEntityNotFoundExceptionDTO("Group Id " + groupId + " not found");
    }
}
