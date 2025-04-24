package com.cursos.ec.testalquimia.messages.response;

public record GenericRespDTO<T>(
        String status,
        String message,
        T data
) {
}
