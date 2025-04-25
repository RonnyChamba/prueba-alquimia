package com.cursos.ec.testalquimia.mappers;

import com.cursos.ec.testalquimia.entities.User;
import com.cursos.ec.testalquimia.messages.request.UserReqDTO;
import com.cursos.ec.testalquimia.messages.response.UserRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User toEntity(UserReqDTO userReqDTO);

    UserRespDTO toResp(User user);

    List<UserRespDTO> toListResp(List<User> users);
}
