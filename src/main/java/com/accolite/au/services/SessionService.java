package com.accolite.au.services;

import com.accolite.au.dto.SessionDTO;
import com.accolite.au.dto.SessionListResponseDTO;
import com.accolite.au.dto.SessionResponseDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.models.Session;

import java.util.List;

public interface SessionService {


    SuccessResponseDTO deleteSession(int SessionId);

    void addSession(SessionDTO sessionDTO);

    List<SessionDTO> getSessions(Integer batchId);
}
