package com.accolite.au.services;

import com.accolite.au.dto.SessionListResponseDTO;
import com.accolite.au.dto.SessionResponseDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.models.Session;

public interface SessionService {

    Session addSession(int batchId, Session session);

    SessionListResponseDTO getAllSessions(int SessionId);

    SessionResponseDTO getSession(int SessionId);

    SuccessResponseDTO deleteSession(int SessionId);

    SessionResponseDTO updateSession(int SessionId, Session Session);
}
