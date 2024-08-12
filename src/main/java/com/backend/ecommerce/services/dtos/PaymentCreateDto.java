package com.backend.ecommerce.services.dtos;

import com.backend.ecommerce.entities.enums.PaymentMethod;
import com.backend.ecommerce.entities.enums.PaymentStatus;

import java.util.UUID;

public record PaymentCreateDto(UUID orderId,
                               Double amount,
                               PaymentStatus status,
                               PaymentMethod method) {
}
