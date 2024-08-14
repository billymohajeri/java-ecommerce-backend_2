package com.backend.ecommerce.dtos.payment;

import com.backend.ecommerce.entities.enums.PaymentMethod;
import com.backend.ecommerce.entities.enums.PaymentStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PaymentUpdateDto(@DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero") @NotNull(message = "Amount cannot be null") Double amount,
                               @NotNull(message = "Payment status cannot be null") PaymentStatus status,
                               @NotNull(message = "Payment method cannot be null") PaymentMethod method) {
}
