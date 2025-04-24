package com.cursos.ec.testalquimia.messages.response;

public record UserRespDTO(
        Long id,
        String username,
        String socialReason,
        String createdAt
) {
}
