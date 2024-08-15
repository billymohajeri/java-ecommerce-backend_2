package com.backend.ecommerce.dtos.cart;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CartDto(@NotBlank UUID userId,@NotBlank UUID productId, @NotBlank int quantity) {
}
