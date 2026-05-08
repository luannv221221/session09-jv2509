package com.ra.mapper;

import com.ra.dto.user.request.UserRegisterRequestDTO;
import com.ra.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRegisterMapper {
    UserRegisterRequestDTO toUserRegisterRequestDTO(User user);

    User toUser(UserRegisterRequestDTO userRegisterRequestDTO);
}
