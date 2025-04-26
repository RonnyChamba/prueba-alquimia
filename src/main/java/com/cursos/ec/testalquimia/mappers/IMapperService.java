package com.cursos.ec.testalquimia.mappers;

import com.cursos.ec.testalquimia.entities.Customer;
import com.cursos.ec.testalquimia.messages.request.CustomerReqDTO;

public interface IMapperService {

    Customer createModelCustomer(CustomerReqDTO data);
}
