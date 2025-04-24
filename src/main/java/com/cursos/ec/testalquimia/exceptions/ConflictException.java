package com.cursos.ec.testalquimia.exceptions;

import org.springframework.http.HttpStatus;

public class ConflictException extends GenericException {

    private static final long serialVersionUID = 1L;

    public ConflictException(String detail) {
        super(detail, HttpStatus.CONFLICT.value());
    }
}
