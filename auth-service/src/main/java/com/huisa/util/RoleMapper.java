package com.huisa.util;

import com.huisa.dtos.request.RoleRequest;
import com.huisa.dtos.response.RoleResponse;
import com.huisa.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "userRoles", ignore = true)
    Role toEntity(RoleRequest roleRequest);

    RoleResponse toDto(Role role);

    List<RoleResponse> toDtoList(List<Role> roles);

}
