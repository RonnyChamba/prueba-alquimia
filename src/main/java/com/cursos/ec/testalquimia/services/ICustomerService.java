package com.cursos.ec.testalquimia.services;

import com.cursos.ec.testalquimia.exceptions.GenericException;
import com.cursos.ec.testalquimia.messages.request.AddressReqDTO;
import com.cursos.ec.testalquimia.messages.request.CustomerReqDTO;
import com.cursos.ec.testalquimia.messages.request.GenericReqDTO;
import com.cursos.ec.testalquimia.messages.response.AddressRespDTO;
import com.cursos.ec.testalquimia.messages.response.CustomerRespDTO;
import com.cursos.ec.testalquimia.messages.response.CustomerUpdateReqDTO;
import com.cursos.ec.testalquimia.messages.response.GenericRespDTO;

import java.util.List;

public interface ICustomerService {

    /**
     * * Method to save a customer
     *
     * @param reqDTO : GenericReqDTO<CustomerReqDTO>
     * @return : GenericRespDTO<CustomerRespDTO>
     * @throws GenericException : GenericException
     */
    GenericRespDTO<CustomerRespDTO> saveCustomer(GenericReqDTO<CustomerReqDTO> reqDTO) throws GenericException;


    /**
     * Method to get all customers by filter
     *
     * @param paramFilter : String
     * @return : GenericRespDTO<List<CustomerRespDTO>>
     * @throws GenericException : GenericException
     */
    GenericRespDTO<List<CustomerRespDTO>> getAllCustomers(String paramFilter) throws GenericException;

    /**
     * Method to update a customer
     *
     * @param id     : Long
     * @param reqDTO : GenericReqDTO<CustomerUpdateReqDTO>
     * @return : GenericRespDTO<CustomerRespDTO>
     * @throws GenericException : GenericException
     */
    GenericRespDTO<CustomerRespDTO> updateCustomer(Long id, GenericReqDTO<CustomerUpdateReqDTO> reqDTO) throws GenericException;

    /**
     * Method to delete a customer
     *
     * @param id : Long
     * @throws GenericException : GenericException
     */
    void deleteCustomer(Long id) throws GenericException;

    /**
     * Method to add an address to a customer
     *
     * @param id     : Long
     * @param reqDTO : GenericReqDTO<AddressReqDTO>
     * @throws GenericException : GenericException
     */
    void addAddressCustomer(Long id, GenericReqDTO<AddressReqDTO> reqDTO) throws GenericException;

    /**
     * Method to get all addresses of a customer
     *
     * @param id : Long
     * @return : GenericRespDTO<List<AddressRespDTO>>
     * @throws GenericException : GenericException
     */
    GenericRespDTO<List<AddressRespDTO>> getAllAddressesCustomer(Long id) throws GenericException;
}
