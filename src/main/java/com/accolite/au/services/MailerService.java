package com.accolite.au.services;

import com.accolite.au.dto.SessionDTO;
import com.accolite.au.dto.TrainerDTO;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;

public interface MailerService {
    void SendMail(TrainerDTO trainerDTO, SessionDTO sessionDTO) throws IOException;

    @Async
    void SendMockMail() throws IOException;
}
