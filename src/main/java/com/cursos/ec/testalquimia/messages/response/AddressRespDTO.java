package com.cursos.ec.testalquimia.messages.response;

public record AddressRespDTO(
        Long id,
        String province,
        Boolean mainAddress,
        String city,
        String address
) {
}
