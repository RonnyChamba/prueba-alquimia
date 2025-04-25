package com.cursos.ec.testalquimia.security.jwt;

import com.cursos.ec.testalquimia.messages.response.GenericRespDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        var genericRes = GenericRespDTO.<String>
                        builder()
                .status("ERROR")
                .message("Token not valid or not authorize")
                .build();
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(genericRes));
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().flush();
        response.getWriter().close();


    }
}
