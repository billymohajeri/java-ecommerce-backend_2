package com.backend.ecommerce.dtos.order;

import com.backend.ecommerce.entities.enums.OrderStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderCreateDto(UUID id,
                             @NotNull(message = "User id is required!") UUID userId,
                             @NotNull(message = "Date and time are required")
                             @PastOrPresent(message = "Date and time must be in the past or present")
                             LocalDateTime dateTime,
                             String comments,
                             @NotNull(message = "Order status is required") OrderStatus status,
                             @NotBlank(message = "Address is required")
                             @Size(max = 255, message = "Address should not exceed 255 characters")
                             String address) {
}
