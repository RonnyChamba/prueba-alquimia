package com.cursos.ec.testalquimia.security.controller;


import com.cursos.ec.testalquimia.exceptions.GenericException;
import com.cursos.ec.testalquimia.messages.request.GenericReqDTO;
import com.cursos.ec.testalquimia.messages.response.GenericRespDTO;
import com.cursos.ec.testalquimia.security.dtos.AuthReqDTO;
import com.cursos.ec.testalquimia.security.dtos.AuthResDTO;
import com.cursos.ec.testalquimia.security.service.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<GenericRespDTO<AuthResDTO>> authLogin(@Valid @RequestBody GenericReqDTO<AuthReqDTO> reqDTO) throws GenericException {
        return ResponseEntity.ok(authService.signIn(reqDTO));
    }


}
