package com.backend.ecommerce.dtos;

import com.backend.ecommerce.entities.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderDto(UUID id,
                       UUID userId,
                       LocalDateTime dateTime,
                       String comments,
                       OrderStatus status,
                       String address) {
}
