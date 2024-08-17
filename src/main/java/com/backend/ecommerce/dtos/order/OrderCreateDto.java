package com.backend.ecommerce.dtos.order;

import com.backend.ecommerce.entities.enums.OrderStatus;
import com.backend.ecommerce.shared.exceptions.ErrorConstants;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderCreateDto(
        UUID id,

        @NotNull(message = ErrorConstants.ErrorMessage.USER_ID_REQUIRED)
        UUID userId,

        @NotNull(message = ErrorConstants.ErrorMessage.ORDER_DATE_DOES_NOT_EXIST)

        @PastOrPresent(message = ErrorConstants.ErrorMessage.ORDER_DATE_LIMIT)

        LocalDateTime dateTime,

        String comments,

        @NotNull(message = ErrorConstants.ErrorMessage.ORDER_STATUS_DOES_NOT_EXIST)
        OrderStatus status,

        @NotBlank(message = ErrorConstants.ErrorMessage.ORDER_ADDRESS_DOES_NOT_EXIST)

        @Size(max = 255, message = ErrorConstants.ErrorMessage.ORDER_ADDRESS_LIMIT)

        String address) {
}
