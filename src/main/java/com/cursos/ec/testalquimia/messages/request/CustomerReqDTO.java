package com.cursos.ec.testalquimia.messages.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CustomerReqDTO(
        @NotBlank(message = "The fullName field is required")
        @Size(min = 3, max = 100, message = "The fullName field must be between 3 and 100 characters")
        String fullName,
        @NotBlank(message = "The identification field is required")
        @Size(min = 10, max = 13, message = "The identification field must be between 10 and 13 characters")
        String identification,
        @NotBlank(message = "The identificationType field is required")
        @Size(min = 3, max = 3, message = "The identificationType field must be 3 characters, for example: 'RUC' or 'CED'")
        String identificationType,
        @NotBlank(message = "The email field is required")
        @Email(message = "The email field must be a valid email")
        @Size(min = 5, max = 100, message = "The email field must be between 5 and 100 characters")
        String email,
        @NotBlank(message = "The cellphone field is required")
        @Size(min = 10, max = 10, message = "The cellphone field must be 10 characters")
        String cellphone,
        @NotNull(message = "The address field is required")
        @Valid
        AddressReqDTO mainAddress
) {
}
