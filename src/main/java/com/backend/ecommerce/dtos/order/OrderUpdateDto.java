package com.backend.ecommerce.dtos.order;

import com.backend.ecommerce.entities.enums.OrderStatus;
import com.backend.ecommerce.shared.exceptions.ErrorConstants;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderUpdateDto(
        String comments,

        OrderStatus status,

        @Size(max = 255, message = ErrorConstants.ErrorMessage.ORDER_ADDRESS_LIMIT)
        String address) {
}
