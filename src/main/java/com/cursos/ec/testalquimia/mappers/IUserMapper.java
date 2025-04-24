package com.cursos.ec.testalquimia.mappers;

import com.cursos.ec.testalquimia.entities.User;
import com.cursos.ec.testalquimia.messages.request.UserReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User toEntity(UserReqDTO userReqDTO);
}
