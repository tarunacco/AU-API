package com.accolite.au.services;

import com.google.auth.Credentials;
import com.google.firebase.auth.internal.GetAccountInfoResponse;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService {
    User getUser();

    Credentials getCredentials();

    String getBearerToken(HttpServletRequest request);
}
