package com.cursos.ec.testalquimia.exceptions;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class NotFoundException extends GenericException {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final Integer code = 45;

    public NotFoundException(String detail) {
        super(detail, HttpStatus.NOT_FOUND.value());
    }
}
