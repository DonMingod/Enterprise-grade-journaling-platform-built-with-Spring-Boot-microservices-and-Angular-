package com.huisa.dtos.response;

import lombok.Builder;

@Builder
public record UserRoleResponse(

        UserResponse user,
        RoleResponse role
) {
}
