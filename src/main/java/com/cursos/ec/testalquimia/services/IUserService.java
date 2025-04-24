package com.cursos.ec.testalquimia.services;

import com.cursos.ec.testalquimia.exceptions.GenericException;
import com.cursos.ec.testalquimia.messages.request.GenericReqDTO;
import com.cursos.ec.testalquimia.messages.request.UserReqDTO;
import com.cursos.ec.testalquimia.messages.response.GenericRespDTO;

public interface IUserService {

    GenericRespDTO<String> saveUser(GenericReqDTO<UserReqDTO> genericReqDTO) throws GenericException;
}
