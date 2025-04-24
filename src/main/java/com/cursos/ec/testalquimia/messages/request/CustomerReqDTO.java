package com.cursos.ec.testalquimia.messages.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReqDTO {
    private String name;
    private String lastName;
    private List<AddressReqDTO> address;
}
