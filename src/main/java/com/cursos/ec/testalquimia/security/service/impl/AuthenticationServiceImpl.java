package com.cursos.ec.testalquimia.security.service.impl;

import com.cursos.ec.testalquimia.exceptions.BadCredentialException;
import com.cursos.ec.testalquimia.exceptions.GenericException;
import com.cursos.ec.testalquimia.messages.request.GenericReqDTO;
import com.cursos.ec.testalquimia.messages.response.GenericRespDTO;
import com.cursos.ec.testalquimia.security.dtos.AuthReqDTO;
import com.cursos.ec.testalquimia.security.dtos.AuthResDTO;
import com.cursos.ec.testalquimia.security.jwt.JwtUtils;
import com.cursos.ec.testalquimia.security.service.IAuthService;
import com.cursos.ec.testalquimia.util.GeneralUtil;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthService {

    private static final Logger LOGGER = LogManager.getLogger(AuthenticationServiceImpl.class);
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final Validator validator;

    @Override
    @Transactional(readOnly = true)
    public GenericRespDTO<AuthResDTO> signIn(GenericReqDTO<AuthReqDTO> reqDTO) throws GenericException {

        try {
            LOGGER.info("Login Req: {}", reqDTO);
            var loginReq = reqDTO.payload();
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginReq.username(),
                            loginReq.password()
                    )
            );
            // Set the authentication in the context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            var userDetails = (UserDetails) authentication.getPrincipal();

            var jwtToken = jwtUtils.generateToken(userDetails);
            return GeneralUtil.buildGenericSuccessResp(new AuthResDTO(jwtToken), "Login exitoso");

        } catch (AuthenticationException ex) {
            throw new BadCredentialException("Credenciales Incorrectas");
        } catch (Exception ex) {
            throw new GenericException("Ocurrio un inesperado al autenticar el usuario", ex);
        }
    }
}
