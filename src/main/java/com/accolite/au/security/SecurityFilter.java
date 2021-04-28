package com.accolite.au.security;

import com.accolite.au.exceptionHandlers.UserAuthenticationException;
import com.accolite.au.services.SecurityService;
import com.accolite.au.utils.ValidatorFunctions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
@Slf4j
public class SecurityFilter extends OncePerRequestFilter {
    private final SecurityService securityService;
    private final ValidatorFunctions validatorFunctions;

    public SecurityFilter(SecurityService securityService, ValidatorFunctions validatorFunctions) {
        this.securityService = securityService;
        this.validatorFunctions = validatorFunctions;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            verifyToken(request);
        } catch (UserAuthenticationException e) {
            throw new ServletException();
        }
        filterChain.doFilter(request, response);
    }

    protected void verifyToken(HttpServletRequest request) throws UserAuthenticationException {
        FirebaseToken decodedToken = null;
        String token = securityService.getBearerToken(request);
        logger.info("Token is: " + token);
        try {
            if (token == null) {
                logger.info("Token is Null");
            } else {
                if (token != null && !token.equalsIgnoreCase("undefined")) {
                    decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
                }
            }
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            logger.error("Firebase Exception: ", e);
        }

        User user = firebaseTokenToUserDto(decodedToken);
        if (user != null && validatorFunctions.isWithinOrganization(user.getUsername())) {
            logger.info("User Valid" + user.toString());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );
            usernamePasswordAuthenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            logger.info("Setting Authentication " + usernamePasswordAuthenticationToken.toString());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        else{
            logger.info("User Invalid");
            throw new UserAuthenticationException("User Invalid");
        }
    }

    private User firebaseTokenToUserDto(FirebaseToken decodedToken) {
        User user = null;
        if (decodedToken != null) {
            user = new User(decodedToken.getEmail(), decodedToken.getUid(), new ArrayList<>());
        }
        return user;
    }
}