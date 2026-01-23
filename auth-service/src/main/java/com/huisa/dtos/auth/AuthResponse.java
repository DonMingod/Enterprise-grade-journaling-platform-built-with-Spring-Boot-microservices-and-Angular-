package com.huisa.dtos.auth;

import lombok.Builder;

import java.util.List;

@Builder
public record AuthResponse(
        Long id,
        String token,
        String username,
        String email,
        List<String>roles
) {
}
