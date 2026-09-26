package com.gabriel.devgram.security;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.gabriel.devgram.controller.exceptions.StandardError;
import tools.jackson.databind.ObjectMapper;

@Component
public class JWTAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        StandardError error = new StandardError(
                System.currentTimeMillis(),
                403,
                "Forbidden",
                "Você não tem permissão para acessar este recurso",
                request.getRequestURI()
        );

        response.setStatus(403);
        response.setContentType("application/json");
        response.getWriter().append(new ObjectMapper().writeValueAsString(error));
    }
}