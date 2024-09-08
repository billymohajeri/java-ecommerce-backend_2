package com.backend.ecommerce.dtos.order;

import com.backend.ecommerce.entities.enums.OrderStatus;
import com.backend.ecommerce.shared.exceptions.ErrorConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record OrderUpdateDto(

        String comments,

        @NotNull
        OrderStatus status,

        @Size(max = 255, message = ErrorConstants.ErrorMessage.ORDER_ADDRESS_LIMIT)
        String address) {
}