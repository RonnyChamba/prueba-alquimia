package com.cursos.ec.testalquimia.security.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthReqDTO(
        @NotBlank(message = "The username field is required")
        String username,
        @NotBlank(message = "The password field is required")
        String password) {
}
