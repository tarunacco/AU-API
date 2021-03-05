package com.accolite.au.services.impl;

import com.accolite.au.dto.*;
import com.accolite.au.mappers.SessionMapper;
import com.accolite.au.models.Batch;
import com.accolite.au.models.Session;
import com.accolite.au.repositories.SessionRepository;
import com.accolite.au.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;
    private final EntityManager entityManager;

    public SessionServiceImpl(SessionRepository sessionRepository, SessionMapper sessionMapper, EntityManager entityManager) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
        this.entityManager = entityManager;
    }


    @Override
    public SuccessResponseDTO deleteSession(int SessionId){
        if(sessionRepository.existsById(SessionId)){
            sessionRepository.deleteById(SessionId);
            return new SuccessResponseDTO("Session with id : " + SessionId + " deleted Successfully", HttpStatus.OK);
        }
        throw new CustomEntityNotFoundExceptionDTO("Session with id : " + SessionId + " not Found");
    }


    @Override
    public void addSession(SessionDTO sessionDTO) {
        Session session = sessionMapper.toSession(sessionDTO);
        Batch batchReference = entityManager.getReference(Batch.class, sessionDTO.getBatchId());
        session.setBatch(batchReference);
        sessionRepository.save(session);
    }

    public List<SessionDTO> getSessions(Integer batchId) {
        return sessionMapper.toSessionDTOs(this.sessionRepository.findAllByBatch_BatchId(batchId));
    }
}
