package com.gabriel.devgram.security;

import com.gabriel.devgram.controller.exceptions.StandardError;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        StandardError error = new StandardError(
                System.currentTimeMillis(),
                401,
                "Unauthorized",
                "Você precisa estar autenticado para acessar este recurso",
                request.getRequestURI()
        );

        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().append(new ObjectMapper().writeValueAsString(error));
    }
}