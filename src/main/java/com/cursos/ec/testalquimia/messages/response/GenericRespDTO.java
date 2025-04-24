package com.cursos.ec.testalquimia.messages.response;

import lombok.Builder;

@Builder
public record GenericRespDTO<T>(
        String status,
        String message,
        T data
) {
}
