package com.huisa.dto;


import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DiaryEntryResponse(

        Long id,
        String title,
        String content,
        Long userId,
        String mood,
        String location,
        String weather,
        String tags,
        Boolean isFavorite,
        Boolean isPrivate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
