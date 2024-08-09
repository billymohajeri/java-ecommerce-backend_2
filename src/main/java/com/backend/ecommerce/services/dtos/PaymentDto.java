package com.backend.ecommerce.services.dtos;

import com.backend.ecommerce.entities.enums.PaymentMethod;
import com.backend.ecommerce.entities.enums.PaymentStatus;

import java.util.UUID;

public record PaymentDto(UUID id,
                         UUID order_id,
                         Double amount,
                         PaymentStatus status,
                         PaymentMethod method) {
}
