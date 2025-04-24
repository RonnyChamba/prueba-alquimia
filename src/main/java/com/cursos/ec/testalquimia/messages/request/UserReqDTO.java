package com.cursos.ec.testalquimia.messages.request;


import jakarta.validation.constraints.NotBlank;

public record UserReqDTO(

        @NotBlank(message = "The username field id cannot be blank")
        String username,
        @NotBlank(message = "The password field id cannot be blank")
        String password,
        @NotBlank(message = "The socialReason field id cannot be blank")
        String socialReason
    ) {
}
