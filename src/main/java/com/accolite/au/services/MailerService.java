package com.accolite.au.services;

import com.accolite.au.dto.SessionDTO;
import com.accolite.au.dto.TrainerDTO;

import java.io.IOException;

public interface MailerService {
    void SendMail(TrainerDTO trainerDTO, SessionDTO sessionDTO) throws IOException;

    void SendMockMail() throws IOException;
}
