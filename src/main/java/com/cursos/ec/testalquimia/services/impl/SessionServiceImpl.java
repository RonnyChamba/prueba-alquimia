package com.cursos.ec.testalquimia.services.impl;

import com.cursos.ec.testalquimia.services.ISessionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements ISessionService {
    @Override
    public String retrieveUsernameFromContext() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}
