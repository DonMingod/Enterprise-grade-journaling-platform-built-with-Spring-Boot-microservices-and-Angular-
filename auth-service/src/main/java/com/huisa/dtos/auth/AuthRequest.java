package com.huisa.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AuthRequest(

        @NotBlank(message = "Email or Username must not be blank")
        String emailOrUsername,

        @NotBlank(message = "Password must not be blank")
        String password
        ) {
}
