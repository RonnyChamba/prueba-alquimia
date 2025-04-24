package com.cursos.ec.testalquimia.exceptions;

import lombok.Getter;

import java.io.Serial;
import java.util.Objects;

@Getter
public class GenericException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;
    private final Integer errorCode;

    public GenericException(String detail, Integer errorCode) {
        super(detail);
        if (Objects.nonNull(errorCode)) {
            this.errorCode = errorCode;
        } else this.errorCode = 0;

    }

    public GenericException(String detail) {
        super(detail);
        errorCode = 0;

    }

    public GenericException(String detail, Exception ex) {
        super(detail, ex);
        errorCode = 0;

    }

    public GenericException(String detail, Integer errorCode, Exception ex) {
        super(detail, ex);
        this.errorCode = errorCode;

    }
}
