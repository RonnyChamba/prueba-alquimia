package com.cursos.ec.testalquimia.services.impl;

import com.cursos.ec.testalquimia.entities.Customer;
import com.cursos.ec.testalquimia.entities.CustomerAddress;
import com.cursos.ec.testalquimia.entities.User;
import com.cursos.ec.testalquimia.exceptions.ConflictException;
import com.cursos.ec.testalquimia.exceptions.GenericException;
import com.cursos.ec.testalquimia.exceptions.NotFoundException;
import com.cursos.ec.testalquimia.mappers.ICustomerMapper;
import com.cursos.ec.testalquimia.messages.request.AddressReqDTO;
import com.cursos.ec.testalquimia.messages.request.CustomerReqDTO;
import com.cursos.ec.testalquimia.messages.request.GenericReqDTO;
import com.cursos.ec.testalquimia.messages.response.CustomerRespDTO;
import com.cursos.ec.testalquimia.messages.response.CustomerUpdateReqDTO;
import com.cursos.ec.testalquimia.messages.response.GenericRespDTO;
import com.cursos.ec.testalquimia.repository.ICustomerRepository;
import com.cursos.ec.testalquimia.repository.IUserRepository;
import com.cursos.ec.testalquimia.services.ICustomerService;
import com.cursos.ec.testalquimia.services.ISessionService;
import com.cursos.ec.testalquimia.util.IdentificationUtil;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements ICustomerService {

    private static final Logger LOGGER = LogManager.getLogger(CustomerServiceImp.class);
    private final ICustomerRepository customerRepository;
    private final ISessionService sessionService;
    private final IUserRepository userRepository;

    @Transactional
    @Override
    public GenericRespDTO<CustomerRespDTO> saveCustomer(GenericReqDTO<CustomerReqDTO> reqDTO) throws GenericException {

        LOGGER.info("Customer Req: {}", reqDTO);

        var data = reqDTO.payload();

        IdentificationUtil.validateIdentification(data.identificationType(), data.identification());
        validateExistsCustomer(data.identification());

        var user = retrieveUserFromContext();
        var customer = createModelCustomer(data);
        customer.setUser(user);

        var address = createModelAddress(data.mainAddress());
        customer.addAddress(address);
        var userSaved = customerRepository.save(customer);
        LOGGER.info("Customer saved: {}", userSaved.getId());

        return GenericRespDTO.<CustomerRespDTO>
                        builder()
                .status("OK")
                .data(ICustomerMapper.INSTANCE.toCustomerRespDTO(userSaved))
                .message("Customer saved")
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public GenericRespDTO<List<CustomerRespDTO>> getAllCustomers(String paramFilter) throws GenericException {

        LOGGER.info("Get all customers with filter: {}", paramFilter);

        var paramSearch = Objects.requireNonNullElse(paramFilter, "").trim().toUpperCase();

        var username = sessionService.retrieveUsernameFromContext();
        var customers = customerRepository.findAllCustomerByUser(username, paramSearch);

        var listCustomers = ICustomerMapper.INSTANCE.toListCustomerRespDTO(customers);

        return GenericRespDTO.<List<CustomerRespDTO>>
                        builder()
                .status("OK")
                .data(listCustomers)
                .message(listCustomers.isEmpty() ? "No customers found" : "Customers found")
                .build();
    }

    @Transactional
    @Override
    public GenericRespDTO<CustomerRespDTO> updateCustomer(Long id, GenericReqDTO<CustomerUpdateReqDTO> reqDTO) throws GenericException {

        LOGGER.info("Update customer with id: {}, req: {}", id, reqDTO);

        var data = reqDTO.payload();
        var customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        IdentificationUtil.validateIdentification(data.identificationType(), data.identification());
        if (!customer.getIdentification().equals(data.identification())) {
            validateIdentificationNotInUse(customer, data.identification());
        }
        setNewDataCustomer(customer, data);
        var customerSaved = customerRepository.save(customer);

        LOGGER.info("Customer updated: {}", customerSaved.getId());

        return GenericRespDTO.<CustomerRespDTO>
                        builder()
                .status("OK")
                .data(ICustomerMapper.INSTANCE.toCustomerRespDTO(customerSaved))
                .message("Customer updated")
                .build();
    }

    private void setNewDataCustomer(Customer customer, CustomerUpdateReqDTO data) {
        customer.setIdentification(data.identification());
        customer.setFullName(data.fullName());
        customer.setIdentificationType(data.identificationType());
        customer.setEmail(data.email());
        customer.setCellphone(data.cellphone());
    }

    /**
     * Method to validate if the identification is already in use by another customer
     *
     * @param customer       : Customer
     * @param identification : String
     * @throws GenericException : GenericException
     */
    private void validateIdentificationNotInUse(Customer customer, String identification) throws GenericException {
        if (customerRepository.existsByIdentificationAndIdNot(identification, customer.getId())) {
            throw new ConflictException("Customer already exists");
        }
    }

    private Customer createModelCustomer(CustomerReqDTO data) {
        var customer = ICustomerMapper.INSTANCE.toEntity(data);
        customer.setProvince(data.mainAddress().province());
        customer.setCity(data.mainAddress().city());
        customer.setAddress(data.mainAddress().address());
        return customer;
    }

    private CustomerAddress createModelAddress(AddressReqDTO addressReqDTO) {
        var address = ICustomerMapper.INSTANCE.toEntityAddress(addressReqDTO);
        address.setMainAddress(true);
        return address;
    }

    private void validateExistsCustomer(String identification) throws GenericException {
        if (customerRepository.existsByIdentification(identification)) {
            throw new ConflictException("Customer already exists");
        }
    }

    private User retrieveUserFromContext() throws GenericException {
        var username = sessionService.retrieveUsernameFromContext();
        LOGGER.info("Username User Login: {}", username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }
}
