package com.cursos.ec.testalquimia.controller;

import com.cursos.ec.testalquimia.exceptions.GenericException;
import com.cursos.ec.testalquimia.messages.request.AddressReqDTO;
import com.cursos.ec.testalquimia.messages.request.CustomerReqDTO;
import com.cursos.ec.testalquimia.messages.request.GenericReqDTO;
import com.cursos.ec.testalquimia.messages.response.CustomerUpdateReqDTO;
import com.cursos.ec.testalquimia.services.ICustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {

    private final ICustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody GenericReqDTO<CustomerReqDTO> genericReqDTO) throws GenericException {
        var response = customerService.saveCustomer(genericReqDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers(@RequestParam(required = false) String paramFilter) throws GenericException {
        var response = customerService.getAllCustomers(paramFilter);

        return new ResponseEntity<>(response, response.data().isEmpty()
                ? HttpStatus.NO_CONTENT
                : HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @Valid @RequestBody GenericReqDTO<CustomerUpdateReqDTO> genericReqDTO) throws GenericException {
        var response = customerService.updateCustomer(id, genericReqDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) throws GenericException {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/address")
    public ResponseEntity<?> addAddressCustomer(@PathVariable Long id, @Valid @RequestBody GenericReqDTO<AddressReqDTO> genericReqDTO) throws GenericException {
        customerService.addAddressCustomer(id, genericReqDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/address")
    public ResponseEntity<?> getAllAddressCustomer(@PathVariable Long id) throws GenericException {
        var response = customerService.getAllAddressesCustomer(id);
        return new ResponseEntity<>(response, response.data().isEmpty()
                ? HttpStatus.NO_CONTENT
                : HttpStatus.OK);
    }
}
