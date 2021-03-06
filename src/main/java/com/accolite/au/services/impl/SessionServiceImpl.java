package com.accolite.au.services.impl;

import com.accolite.au.dto.*;
import com.accolite.au.mappers.SessionMapper;
import com.accolite.au.models.Batch;
import com.accolite.au.models.Session;
import com.accolite.au.models.Trainer;
import com.accolite.au.repositories.BatchRepository;
import com.accolite.au.repositories.SessionRepository;
import com.accolite.au.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final BatchRepository batchRepository;
    private final SessionMapper sessionMapper;
    private final EntityManager entityManager;

    public SessionServiceImpl(SessionRepository sessionRepository, BatchRepository batchRepository, SessionMapper sessionMapper, EntityManager entityManager) {
        this.sessionRepository = sessionRepository;
        this.batchRepository = batchRepository;
        this.sessionMapper = sessionMapper;
        this.entityManager = entityManager;
    }

    @Override
    public SuccessResponseDTO deleteSession(int sessionId){
        if(sessionRepository.existsById(sessionId)){
            sessionRepository.deleteById(sessionId);
            return new SuccessResponseDTO("Session with id : " + sessionId + " deleted Successfully", HttpStatus.OK);
        }
        throw new CustomEntityNotFoundExceptionDTO("Session with id : " + sessionId + " not Found");
    }


    @Override
    public SessionDTO addOrUpdateSession(SessionDTO sessionDTO) {
        if(batchRepository.existsById(sessionDTO.getBatchId())) {
            Session session = sessionMapper.toSession(sessionDTO);
            Batch batchReference = entityManager.getReference(Batch.class, sessionDTO.getBatchId());
            //Trainer trainerReference = entityManager.getReference(Trainer.class, sessionDTO.getTrainerId());
            session.setBatch(batchReference);
            //session.setTrainer(trainerReference);
            return sessionMapper.toSessionDTO(sessionRepository.saveAndFlush(session));
        }
        throw new CustomEntityNotFoundExceptionDTO("Batch with id : " + sessionDTO.getBatchId() + " not Found");
    }

    @Override
    public List<SessionDTO> getAllSessions(int batchId) {
        if(batchRepository.existsById(batchId)) {
            return sessionMapper.toSessionDTOs(sessionRepository.findAllByBatch_BatchIdOrderByStartDateAscDaySlotDesc(batchId));
        }
        throw new CustomEntityNotFoundExceptionDTO("Batch with id : " + batchId + " not Found");
    }

    @Override
    public SessionDTO getSession(int sessionId) {
        if (sessionRepository.existsById(sessionId)) {
            return sessionMapper.toSessionDTO(sessionRepository.getOne(sessionId));
        }
        throw new CustomEntityNotFoundExceptionDTO("Session with id : " + sessionId + " not Found");
    }

//    @Override
//    public SessionDTO updateSession(SessionDTO sessionDTO){
//        if(sessionRepository.existsById(sessionDTO.getSessionId())) {
//            Session session = sessionMapper.toSession(sessionDTO);
//            return sessionMapper.toSessionDTO(sessionRepository.saveAndFlush(session));
//        }
//        throw new CustomEntityNotFoundExceptionDTO("Session with id : " + sessionDTO.getSessionId() + " not Found");
//    }
}
