package com.backend.ecommerce.dtos.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CartDto(
        @NotNull
        UUID userId,

        @NotNull
        UUID productId,

        @Min(value = 0, message = "quantity can't be negative")
        int quantity
) {
}