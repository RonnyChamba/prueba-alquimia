package com.cursos.ec.testalquimia.service;


import com.cursos.ec.testalquimia.entities.Customer;
import com.cursos.ec.testalquimia.exceptions.ConflictException;
import com.cursos.ec.testalquimia.exceptions.GenericException;
import com.cursos.ec.testalquimia.exceptions.NotFoundException;
import com.cursos.ec.testalquimia.mappers.IMapperService;
import com.cursos.ec.testalquimia.messages.request.CustomerReqDTO;
import com.cursos.ec.testalquimia.messages.response.CustomerRespDTO;
import com.cursos.ec.testalquimia.mock.MockData;
import com.cursos.ec.testalquimia.repository.ICustomerRepository;
import com.cursos.ec.testalquimia.services.ISessionService;
import com.cursos.ec.testalquimia.services.impl.CustomerServiceImp;
import com.cursos.ec.testalquimia.util.GeneralUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TestCustomer {

    @Mock
    ICustomerRepository customerRepository;

    @Mock
    IMapperService mapperService;

    @InjectMocks
    CustomerServiceImp customerServiceImp;

    @Mock
    ISessionService sessionService;


    @BeforeEach
    void setup() {
        Mockito.reset(customerRepository);
    }

    @DisplayName("Test to check if a customer is saved successfully")
    @Test
    void testSaveCustomer() throws GenericException {

        when(sessionService.retrieveUsernameFromContext()).thenReturn(MockData.user);
        when(customerRepository.existsByIdentification(anyString())).thenReturn(false);
        when(mapperService.createModelCustomer(any(CustomerReqDTO.class))).thenReturn(MockData.customer);
        when(customerRepository.save(any(Customer.class))).thenReturn(MockData.customer);


        var genericResp = customerServiceImp.saveCustomer(GeneralUtil.buildGenericReq(MockData.customerReqDTO));

        verify(customerRepository, times(1)).save(any(Customer.class));
        verify(customerRepository, times(1)).existsByIdentification(anyString());
        verify(mapperService, times(1)).createModelCustomer(any(CustomerReqDTO.class));
        verify(sessionService, times(1)).retrieveUsernameFromContext();

        assertEquals("OK", genericResp.status());
        assertEquals("Customer saved", genericResp.message());
        assertEquals(CustomerRespDTO.class, genericResp.data().getClass());
        assertEquals(MockData.customer.getId(), genericResp.data().id());
        assertEquals(MockData.customer.getIdentification(), genericResp.data().identification());
        assertEquals(MockData.customer.getIdentificationType(), genericResp.data().identificationType());
        assertEquals(MockData.customer.getEmail(), genericResp.data().email());
        assertEquals(MockData.customer.getCellphone(), genericResp.data().cellphone());
        assertEquals(MockData.customer.getProvince(), genericResp.data().province());
        assertEquals(MockData.customer.getCity(), genericResp.data().city());
        assertEquals(MockData.customer.getAddress(), genericResp.data().address());

    }

    @DisplayName("Test to check if a customer is not saved when it already exists")
    @Test
    void testSaveCustomer_HandlerConflictException() {
        when(customerRepository.existsByIdentification(anyString())).thenReturn(true);
        var exception = assertThrows(ConflictException.class, () -> customerServiceImp.saveCustomer(GeneralUtil.buildGenericReq(MockData.customerReqDTO)));
        assertEquals("Customer already exists", exception.getMessage());
    }

    @DisplayName("Test to get all customers")
    @Test
    void testFindAllCustomer() throws GenericException {

        when(sessionService.retrieveUsernameFromContext()).thenReturn(MockData.user);
        when(customerRepository.findAllCustomerByUser(anyString(), anyString())).thenReturn(List.of(MockData.customer));

        var genericResp = customerServiceImp.getAllCustomers(null);
        verify(customerRepository, times(1)).findAllCustomerByUser(anyString(), anyString());
        assertEquals("OK", genericResp.status());
        assertEquals("Customers found", genericResp.message());
        assertEquals(1, genericResp.data().size());
        assertEquals(MockData.customer.getId(), genericResp.data().get(0).id());
    }

    @DisplayName("Test to check if a customer was updated successfully")
    @Test
    void testUpdateCustomer() throws GenericException {


        var customerClone = MockData.cloneCustomer(MockData.customer);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customerClone));
        when(customerRepository.existsByIdentificationAndIdNot(anyString(), anyLong())).thenReturn(false);
        when(customerRepository.save(any(Customer.class))).thenReturn(MockData.customerUpdated);
        var response = customerServiceImp.updateCustomer(1L, GeneralUtil.buildGenericReq(MockData.customerUpdateReqDTO));

        verify(customerRepository, times(1)).findById(anyLong());
        verify(customerRepository, times(1)).existsByIdentificationAndIdNot(anyString(), anyLong());
        verify(customerRepository, times(1)).save(any(Customer.class));
        assertEquals("OK", response.status());
        assertEquals("Customer updated", response.message());
        assertEquals(MockData.customerUpdated.getId(), response.data().id());
        assertEquals(MockData.customerUpdated.getIdentification(), response.data().identification());
        assertEquals(MockData.customerUpdated.getIdentificationType(), response.data().identificationType());
        assertEquals(MockData.customerUpdated.getEmail(), response.data().email());
        assertEquals(MockData.customerUpdated.getCellphone(), response.data().cellphone());
    }

    @DisplayName("Test to check if a customer was updated with a conflict")
    @Test
    void testUpdateCustomer_ConflictException() {

        var customerClone = MockData.cloneCustomer(MockData.customer);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customerClone));
        when(customerRepository.existsByIdentificationAndIdNot(anyString(), anyLong())).thenReturn(true);
        var exception = assertThrows(ConflictException.class, () -> customerServiceImp.updateCustomer(1L, GeneralUtil.buildGenericReq(MockData.customerUpdateReqDTO)));

        assertEquals("Customer already exists", exception.getMessage());

        verify(customerRepository, times(1)).findById(anyLong());
        verify(customerRepository, times(1)).existsByIdentificationAndIdNot(anyString(), anyLong());
        verify(customerRepository, never()).save(any(Customer.class));
    }


    @DisplayName("Test to check if a customer was deleted successfully")
    @Test
    void testDeleteCustomer() throws GenericException {

        when(customerRepository.findById(1L)).thenReturn(Optional.of(MockData.customer));
        customerServiceImp.deleteCustomer(1L);

        verify(customerRepository, times(1)).findById(anyLong());
        verify(customerRepository, times(1)).delete(any(Customer.class));
    }

    @DisplayName("Test to check if exception is thrown when deleting non-existing customer")
    @Test
    void testDeleteCustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        var exception = assertThrows(
                NotFoundException.class,
                () -> customerServiceImp.deleteCustomer(1L)
        );

        assertEquals("Customer not found", exception.getMessage());
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, never()).delete(any());
    }

}
