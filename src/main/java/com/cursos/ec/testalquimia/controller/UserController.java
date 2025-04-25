package com.cursos.ec.testalquimia.controller;

import com.cursos.ec.testalquimia.exceptions.GenericException;
import com.cursos.ec.testalquimia.messages.request.GenericReqDTO;
import com.cursos.ec.testalquimia.messages.request.UserReqDTO;
import com.cursos.ec.testalquimia.messages.response.GenericRespDTO;
import com.cursos.ec.testalquimia.messages.response.UserRespDTO;
import com.cursos.ec.testalquimia.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping
    public ResponseEntity<GenericRespDTO<String>> saveUser(@Valid @RequestBody GenericReqDTO<UserReqDTO> userReqDTO) throws GenericException {

        var response = userService.saveUser(userReqDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<GenericRespDTO<List<UserRespDTO>>> findAllUser() throws GenericException {
        var response = userService.finaAllUser();
        return new ResponseEntity<>(response, response.data().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericRespDTO<UserRespDTO>> findUser(@PathVariable Long id) throws GenericException {
        var response = userService.findUser(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws GenericException {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
