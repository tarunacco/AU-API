package com.accolite.au.services.impl;

import com.accolite.au.dto.CustomEntityNotFoundExceptionDTO;
import com.accolite.au.dto.SessionListResponseDTO;
import com.accolite.au.dto.SessionResponseDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.models.Session;
import com.accolite.au.repositories.BatchRepository;
import com.accolite.au.repositories.SessionRepository;
import com.accolite.au.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final BatchRepository batchRepository;

//    @Override
//    public Session addSession(int batchId, Session session) {
//        session.setBatch(batchRepository.getOne(batchId));
//        Session tempSession = sessionRepository.saveAndFlush(session);
//        System.out.println(tempSession);
//        return tempSession;
//    }

    @Override
    public SessionListResponseDTO getAllSessions(int batchId){
        System.out.println(batchId);
        return new SessionListResponseDTO(sessionRepository.findByBatchId(batchId), HttpStatus.OK);
        
//        if(batchRepository.existsById(batchId)) {
//            return new SessionListResponseDTO(batchRepository.getOne(batchId).getSessions(), HttpStatus.OK);
//        }
//        throw new CustomEntityNotFoundExceptionDTO("Batch with id : " + batchId + " not Found");
    }

    @Override
    public SessionResponseDTO getSession(int SessionId){
        if(sessionRepository.existsById(SessionId) == false){
            throw new CustomEntityNotFoundExceptionDTO("Session with id : " + SessionId + " not Found");
        }
        return new SessionResponseDTO(sessionRepository.getOne(SessionId), HttpStatus.OK);
    }

    @Override
    public SuccessResponseDTO deleteSession(int SessionId){
        if(sessionRepository.existsById(SessionId)){
            sessionRepository.deleteById(SessionId);
            return new SuccessResponseDTO("Session with id : " + SessionId + " deleted Successfully", HttpStatus.OK);
        }
        throw new CustomEntityNotFoundExceptionDTO("Session with id : " + SessionId + " not Found");
    }

    public void updateObject(Session Session, Session tempSession){
        if(tempSession.getSessionName() != null){
            Session.setSessionName(tempSession.getSessionName());
        }
        if(tempSession.getSessionName() != null){
            Session.setSessionName(tempSession.getSessionName());
        }
        if(tempSession.getClassroomTopicId() != null){
            Session.setClassroomTopicId(tempSession.getClassroomTopicId());
        }
    }

    @Override
    public SessionResponseDTO updateSession(int SessionId, Session Session){
        if(sessionRepository.existsById(SessionId)){
            //            sessionRepository.findAndUpdateObject((Session)tempSession, Session Session);
            Session tempSession = sessionRepository.getOne(SessionId);
            this.updateObject(tempSession, Session);
            return new SessionResponseDTO(tempSession, HttpStatus.CREATED);
        }
        throw new CustomEntityNotFoundExceptionDTO("Session with id : " + SessionId + " not Found");
    }
}
