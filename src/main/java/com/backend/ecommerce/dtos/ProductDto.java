package com.backend.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

public record ProductDto(
        UUID id,

        @NotBlank String name,

        @NotBlank double price,

        String description,

        List<String> images,

        @NotBlank String color,

        float rating,

        @NotBlank int stock
) {
}