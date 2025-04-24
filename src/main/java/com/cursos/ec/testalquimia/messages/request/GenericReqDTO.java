package com.cursos.ec.testalquimia.messages.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public record GenericReqDTO<T>(

        @NotBlank(message = "The origin field id cannot be blank")
        String origin,
        @NotNull(message = "The payload field cannot be null")
        @Valid
        T payload
) {
}
