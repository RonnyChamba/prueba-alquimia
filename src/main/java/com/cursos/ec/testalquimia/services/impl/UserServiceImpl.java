package com.cursos.ec.testalquimia.services.impl;


import com.cursos.ec.testalquimia.exceptions.ConflictException;
import com.cursos.ec.testalquimia.exceptions.GenericException;
import com.cursos.ec.testalquimia.exceptions.NotFoundException;
import com.cursos.ec.testalquimia.mappers.IUserMapper;
import com.cursos.ec.testalquimia.messages.request.GenericReqDTO;
import com.cursos.ec.testalquimia.messages.request.UserReqDTO;
import com.cursos.ec.testalquimia.messages.response.GenericRespDTO;
import com.cursos.ec.testalquimia.messages.response.UserRespDTO;
import com.cursos.ec.testalquimia.repository.IUserRepository;
import com.cursos.ec.testalquimia.services.IUserService;
import com.cursos.ec.testalquimia.util.GeneralUtil;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public GenericRespDTO<String> saveUser(GenericReqDTO<UserReqDTO> genericReqDTO) throws GenericException {
        LOGGER.info("Saving user: {}", genericReqDTO.payload());

        if (userRepository.existsByUsername(genericReqDTO.payload().username())) {
            throw new ConflictException("User already exists");

        }
        var user = IUserMapper.INSTANCE.toEntity(genericReqDTO.payload());
        user.setPassword(passwordEncoder.encode(genericReqDTO.payload().password()));
        var userSaved = userRepository.save(user);

        return GeneralUtil.buildGenericSuccessResp(userSaved.getId().toString(),
                "User saved successfully");
    }

    @Transactional(readOnly = true)
    @Override
    public GenericRespDTO<List<UserRespDTO>> finaAllUser() throws GenericException {

        LOGGER.info("Finding all users");
        var users = userRepository.findAll();
        var listDto = IUserMapper.INSTANCE.toListResp(users);

        return GeneralUtil.buildGenericSuccessResp(listDto,
                listDto.isEmpty() ? "No users found" : "Users found successfully");
    }

    @Transactional(readOnly = true)
    @Override
    public GenericRespDTO<UserRespDTO> findUser(Long id) throws GenericException {

        LOGGER.info("Finding user by id: {}", id);
        var user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        var userDto = IUserMapper.INSTANCE.toResp(user);
        return GeneralUtil.buildGenericSuccessResp(userDto,
                "User found successfully");
    }

    @Transactional
    @Override
    public void deleteUser(Long id) throws GenericException {

        LOGGER.info("Deleting user by id: {}", id);
        var user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        userRepository.delete(user);
    }
}
