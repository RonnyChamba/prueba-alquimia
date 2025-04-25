package com.cursos.ec.testalquimia.services;

import com.cursos.ec.testalquimia.exceptions.GenericException;
import com.cursos.ec.testalquimia.messages.request.GenericReqDTO;
import com.cursos.ec.testalquimia.messages.request.UserReqDTO;
import com.cursos.ec.testalquimia.messages.response.GenericRespDTO;
import com.cursos.ec.testalquimia.messages.response.UserRespDTO;

import java.util.List;

public interface IUserService {

    GenericRespDTO<String> saveUser(GenericReqDTO<UserReqDTO> genericReqDTO) throws GenericException;

    GenericRespDTO<List<UserRespDTO>> finaAllUser() throws GenericException;

    GenericRespDTO<UserRespDTO> findUser(Long id) throws GenericException;
}
