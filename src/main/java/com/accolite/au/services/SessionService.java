package com.accolite.au.services;

import com.accolite.au.dto.SessionDTO;
import com.accolite.au.dto.SuccessResponseDTO;

import java.util.List;

public interface SessionService {

    SuccessResponseDTO deleteSession(int SessionId);

    SessionDTO addSession(SessionDTO sessionDTO);

    List<SessionDTO> getAllSessions(int batchId);

    SessionDTO getSession(int sessionId);

    SessionDTO updateSession(SessionDTO sessionDTO);
}
