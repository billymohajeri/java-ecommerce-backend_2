package com.backend.ecommerce.dtos.payment;

import com.backend.ecommerce.entities.enums.PaymentMethod;
import com.backend.ecommerce.entities.enums.PaymentStatus;

import java.util.UUID;

public record PaymentResponseDto(
        UUID id,
        UUID orderId,
        Double amount,
        PaymentStatus status,
        PaymentMethod method) {
}
