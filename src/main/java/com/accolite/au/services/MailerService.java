package com.accolite.au.services;

import com.accolite.au.dto.SessionDTO;
import com.accolite.au.dto.TrainerDTO;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;

public interface MailerService {
    @Async
    void SendMail(int sessionId) throws IOException;

    @Async
    void SendMockMail() throws IOException;
}
