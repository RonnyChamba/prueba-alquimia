package com.cursos.ec.testalquimia.mock;

import com.cursos.ec.testalquimia.entities.Customer;
import com.cursos.ec.testalquimia.entities.CustomerAddress;
import com.cursos.ec.testalquimia.entities.User;
import com.cursos.ec.testalquimia.messages.request.AddressReqDTO;
import com.cursos.ec.testalquimia.messages.request.CustomerReqDTO;
import com.cursos.ec.testalquimia.messages.response.CustomerUpdateReqDTO;

import java.util.Date;

public class MockData {

    public static final Customer customer = new Customer();
    public static final Customer customerUpdated = new Customer();
    public static final User user = new User();

    public static final CustomerUpdateReqDTO customerUpdateReqDTO = new CustomerUpdateReqDTO(
            "John Doe DOS",
            "1034567890001",
            "RUC",
            "johnDOS@gmail.com",
            "0987654322"
    );
    public static final CustomerAddress customerAddress = new CustomerAddress();

    static {

        user.setId(1L);
        user.setUsername("rene");
        user.setPassword("123567");
        user.setCreatedAt(new Date());


        customerAddress.setId(1L);
        customerAddress.setMainAddress(true);
        customerAddress.setProvince("Pichincha");
        customerAddress.setCity("Quito");
        customerAddress.setAddress("Av. Amazonas y 10 de Agosto");

        customer.setId(1L);
        customer.setFullName("John Doe");
        customer.setIdentification("1234567890");
        customer.setIdentificationType("CED");
        customer.setCellphone("0987654321");
        customer.setEmail("Kevin Sanches");
        customer.setCreateAt(new Date());
        customer.setProvince(customerAddress.getProvince());
        customer.setCity(customer.getCity());
        customer.setAddress(customerAddress.getAddress());


        customerUpdated.setId(1L);
        customerUpdated.setFullName(customerUpdateReqDTO.fullName());
        customerUpdated.setIdentification(customerUpdateReqDTO.identification());
        customerUpdated.setIdentificationType(customerUpdateReqDTO.identificationType());
        customerUpdated.setCellphone(customerUpdateReqDTO.cellphone());
        customerUpdated.setEmail(customerUpdateReqDTO.email());


    }

    public static final AddressReqDTO addressReqDTO = new AddressReqDTO(
            "Pichincha",
            "Quito",
            "Av. Amazonas y 10 de Agosto"
    );

    public static final CustomerReqDTO customerReqDTO = new CustomerReqDTO(
            "John Doe",
            "1234567890",
            "CED",
            "john@gmail.com",
            "0987654321",
            addressReqDTO
    );

    public static Customer cloneCustomer(Customer original) {
        Customer clone = new Customer();
        clone.setId(original.getId());
        clone.setIdentification(original.getIdentification());
        clone.setIdentificationType(original.getIdentificationType());
        clone.setEmail(original.getEmail());
        clone.setCellphone(original.getCellphone());
        clone.setProvince(original.getProvince());
        clone.setCity(original.getCity());
        clone.setAddress(original.getAddress());
        clone.setCreateAt(original.getCreateAt());
        clone.setUser(original.getUser());
        return clone;
    }


}
