package com.backend.ecommerce.services.dtos;

import com.backend.ecommerce.entities.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderUpdateDto(UUID id,
                             String comments,
                             OrderStatus status,
                             String address) {
}
