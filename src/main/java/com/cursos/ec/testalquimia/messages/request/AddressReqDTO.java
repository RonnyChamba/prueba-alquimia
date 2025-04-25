package com.cursos.ec.testalquimia.messages.request;

import jakarta.validation.constraints.NotBlank;

public record AddressReqDTO(
        @NotBlank(message = "The province field is required")
        String province,
        @NotBlank(message = "The city field is required")
        String city,
        @NotBlank(message = "The address field is required")
        String address
) {
}
