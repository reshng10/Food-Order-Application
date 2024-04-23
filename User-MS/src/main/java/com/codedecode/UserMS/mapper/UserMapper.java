package com.codedecode.UserMS.mapper;

import com.codedecode.UserMS.dto.UserDto;
import com.codedecode.UserMS.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Users mapUserDTOToUser(UserDto userDTO);
    UserDto mapUserToUserDTO(Users user);

}
