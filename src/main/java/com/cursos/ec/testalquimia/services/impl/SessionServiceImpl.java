package com.cursos.ec.testalquimia.services.impl;

import com.cursos.ec.testalquimia.entities.User;
import com.cursos.ec.testalquimia.exceptions.GenericException;
import com.cursos.ec.testalquimia.exceptions.NotFoundException;
import com.cursos.ec.testalquimia.repository.IUserRepository;
import com.cursos.ec.testalquimia.services.ISessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements ISessionService {

    private final IUserRepository userRepository;

    @Override
    public User retrieveUsernameFromContext() throws GenericException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userDetails = (UserDetails) authentication.getPrincipal();
        var username = userDetails.getUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }
}
