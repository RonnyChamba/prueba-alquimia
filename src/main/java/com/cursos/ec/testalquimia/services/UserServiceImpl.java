package com.cursos.ec.testalquimia.services;


import com.cursos.ec.testalquimia.mappers.IUserMapper;
import com.cursos.ec.testalquimia.messages.request.GenericReqDTO;
import com.cursos.ec.testalquimia.messages.request.UserReqDTO;
import com.cursos.ec.testalquimia.messages.response.GenericRespDTO;
import com.cursos.ec.testalquimia.repository.IUserRepository;
import com.cursos.ec.testalquimia.util.GeneralUtil;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private final IUserRepository userRepository;

    @Override
    public GenericRespDTO<String> saveUser(GenericReqDTO<UserReqDTO> genericReqDTO) {
        LOGGER.info("Saving user: {}", genericReqDTO.payload());
        var user = IUserMapper.INSTANCE.toEntity(genericReqDTO.payload());
        var userSaved = userRepository.save(user);

        return GeneralUtil.buildGenericSuccessResp(userSaved.getId().toString(),
                "User saved successfully");
    }
}
