package com.cursos.ec.testalquimia.messages.response;

public record CustomerRespDTO(
        Long id,
        String fullName,
        String identification,
        String identificationType,
        String email,
        String cellphone,
        String province,
        String city,
        String address,
        String createAt
) {
}
