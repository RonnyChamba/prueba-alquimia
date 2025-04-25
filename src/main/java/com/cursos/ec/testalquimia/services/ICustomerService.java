package com.cursos.ec.testalquimia.services;

import com.cursos.ec.testalquimia.exceptions.GenericException;
import com.cursos.ec.testalquimia.messages.request.CustomerReqDTO;
import com.cursos.ec.testalquimia.messages.request.GenericReqDTO;
import com.cursos.ec.testalquimia.messages.response.CustomerRespDTO;
import com.cursos.ec.testalquimia.messages.response.GenericRespDTO;

public interface ICustomerService {

    /**
     * * Method to save a customer
     *
     * @param reqDTO : GenericReqDTO<CustomerReqDTO>
     * @return : GenericRespDTO<String>
     * @throws GenericException : GenericException
     */
    GenericRespDTO<CustomerRespDTO> saveCustomer(GenericReqDTO<CustomerReqDTO> reqDTO) throws GenericException;
}
