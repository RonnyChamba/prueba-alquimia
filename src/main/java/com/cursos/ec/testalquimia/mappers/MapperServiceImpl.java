package com.cursos.ec.testalquimia.mappers;

import com.cursos.ec.testalquimia.entities.Customer;
import com.cursos.ec.testalquimia.messages.request.CustomerReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapperServiceImpl implements IMapperService {
    @Override
    public Customer createModelCustomer(CustomerReqDTO data) {

        var customer = ICustomerMapper.INSTANCE.toEntity(data);
        customer.setProvince(data.mainAddress().province());
        customer.setCity(data.mainAddress().city());
        customer.setAddress(data.mainAddress().address());
        return customer;
    }
}
