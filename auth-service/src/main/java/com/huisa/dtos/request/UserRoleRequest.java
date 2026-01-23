package com.huisa.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserRoleRequest(

        @NotNull(message = "User ID cannot be blank")
        Long userId,

        @NotNull(message = "Role ID cannot be blank")
        Long roleId
) {
}
