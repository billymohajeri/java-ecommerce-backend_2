package com.backend.ecommerce.dtos.payment;

import com.backend.ecommerce.entities.enums.PaymentMethod;
import com.backend.ecommerce.entities.enums.PaymentStatus;
import com.backend.ecommerce.shared.exceptions.ErrorConstants;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PaymentCreateDto(
        @NotNull(message = ErrorConstants.ErrorMessage.ORDER_ID_REQUIRED)
        UUID orderId,

        @DecimalMin(value = "0.0", inclusive = false,
                message = ErrorConstants.ErrorMessage.PAYMENT_AMOUNT_LIMIT)
        @NotNull(message = ErrorConstants.ErrorMessage.PAYMENT_AMOUNT_REQUIRED)
        Double amount,

        @NotNull(message = ErrorConstants.ErrorMessage.PAYMENT_STATUS_REQUIRED)
        PaymentStatus status,

        @NotNull(message = ErrorConstants.ErrorMessage.PAYMENT_METHOD_REQUIRED)
        PaymentMethod method) {
}
