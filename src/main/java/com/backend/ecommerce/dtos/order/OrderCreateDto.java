package com.backend.ecommerce.dtos.order;

import com.backend.ecommerce.entities.Product;
import com.backend.ecommerce.entities.enums.OrderStatus;
import com.backend.ecommerce.shared.exceptions.ErrorConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderCreateDto(
        UUID id,

        @NotNull(message = ErrorConstants.ErrorMessage.USER_ID_REQUIRED)
        UUID userId,

        LocalDateTime dateTime,

        String comments,

        @NotNull(message = ErrorConstants.ErrorMessage.ORDER_STATUS_DOES_NOT_EXIST)
        OrderStatus status,

        @NotBlank(message = ErrorConstants.ErrorMessage.ORDER_ADDRESS_DOES_NOT_EXIST)
        @Size(max = 255, message = ErrorConstants.ErrorMessage.ORDER_ADDRESS_LIMIT)
        String address,

        @NotNull
        List<Product> products
) {
}