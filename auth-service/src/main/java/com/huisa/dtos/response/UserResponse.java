package com.huisa.dtos.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponse(

        Long id,
        String email,
        String username,
        String firstName,
        String lastName,
        Boolean enabled,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
