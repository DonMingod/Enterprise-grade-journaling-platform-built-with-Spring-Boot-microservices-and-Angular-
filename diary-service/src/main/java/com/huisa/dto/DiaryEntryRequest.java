package com.huisa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record DiaryEntryRequest(

        @NotBlank(message = "Title is required")
        @Size(min = 3, max = 200, message = "Title must be between 3 and 200 characters")
        String title,

        @NotBlank(message = "Content is required")
        @Size(min = 10, message = "Content must be at least 10 characters")
        String content,

        @NotNull(message = "User ID is required")
        Long userId,

        String mood,

        String location,

        String weather,

        @Length(max = 500, message = "Tags debe tener máximo 500 caracteres")
        String tags,

        Boolean isFavorite,

        Boolean isPrivate

) {}
