package com.cursos.ec.testalquimia.security.service;

import com.cursos.ec.testalquimia.exceptions.GenericException;
import com.cursos.ec.testalquimia.messages.request.GenericReqDTO;
import com.cursos.ec.testalquimia.messages.response.GenericRespDTO;
import com.cursos.ec.testalquimia.security.dtos.AuthReqDTO;
import com.cursos.ec.testalquimia.security.dtos.AuthResDTO;

public interface IAuthService {
    GenericRespDTO<AuthResDTO> signIn(GenericReqDTO<AuthReqDTO> reqDTO) throws GenericException;


}
