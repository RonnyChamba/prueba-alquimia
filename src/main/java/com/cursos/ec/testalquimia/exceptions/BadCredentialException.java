package com.cursos.ec.testalquimia.exceptions;

import org.springframework.http.HttpStatus;

public class BadCredentialException extends GenericException {

    private static final long serialVersionUID = 1L;

    public BadCredentialException(String detail) {
        super(detail, HttpStatus.UNAUTHORIZED.value());

    }

}