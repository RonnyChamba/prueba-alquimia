package com.cursos.ec.testalquimia.controller;

import com.cursos.ec.testalquimia.exceptions.GenericException;
import com.cursos.ec.testalquimia.messages.request.CustomerReqDTO;
import com.cursos.ec.testalquimia.messages.request.GenericReqDTO;
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

}
