package com.cursos.ec.testalquimia.messages.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressReqDTO {

    private String address;
    private String city;
}
