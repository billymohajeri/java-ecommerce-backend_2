package com.backend.ecommerce.dtos.order;

import com.backend.ecommerce.entities.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderCreateDto(UUID id,
                             UUID userId,
                             LocalDateTime dateTime,
                             String comments,
                             OrderStatus status,
                             String address) {
}
