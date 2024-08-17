package com.backend.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ReviewDto(

        UUID id,

        @NotBlank UUID productId,

        @NotBlank UUID userId,

        @NotBlank String review,

        float rating,

        List<String> images,

        @NotBlank LocalDateTime dateTime
) {
}
