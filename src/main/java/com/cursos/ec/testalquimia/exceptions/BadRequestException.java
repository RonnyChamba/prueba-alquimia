package com.cursos.ec.testalquimia.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends GenericException {

    private static final long serialVersionUID = 1L;

    public BadRequestException(String detail) {
        super(detail, HttpStatus.BAD_REQUEST.value());
    }
}
