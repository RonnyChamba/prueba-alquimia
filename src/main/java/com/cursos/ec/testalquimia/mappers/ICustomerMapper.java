package com.cursos.ec.testalquimia.mappers;

import com.cursos.ec.testalquimia.entities.Customer;
import com.cursos.ec.testalquimia.entities.CustomerAddress;
import com.cursos.ec.testalquimia.messages.request.AddressReqDTO;
import com.cursos.ec.testalquimia.messages.request.CustomerReqDTO;
import com.cursos.ec.testalquimia.messages.response.CustomerRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ICustomerMapper {

    ICustomerMapper INSTANCE = Mappers.getMapper(ICustomerMapper.class);

    Customer toEntity(CustomerReqDTO customer);

    CustomerAddress toEntityAddress(AddressReqDTO addressReqDTO);

    CustomerRespDTO toCustomerRespDTO(Customer customer);

    List<CustomerRespDTO> toListCustomerRespDTO(List<Customer> customers);
}
