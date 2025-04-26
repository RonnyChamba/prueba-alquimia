package com.cursos.ec.testalquimia.services;

import com.cursos.ec.testalquimia.entities.User;
import com.cursos.ec.testalquimia.exceptions.GenericException;

public interface ISessionService {

    User retrieveUsernameFromContext() throws GenericException;
}
