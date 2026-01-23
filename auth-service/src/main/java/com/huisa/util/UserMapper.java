package com.huisa.util;

import com.huisa.dtos.request.UserRequest;
import com.huisa.dtos.response.UserResponse;
import com.huisa.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "userRoles", ignore = true)
    User toEntity(UserRequest userRequest);

    UserResponse toDto(User user);

    List<UserResponse> toDtoList(List<User> users);


}
