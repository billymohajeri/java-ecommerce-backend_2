package com.backend.ecommerce.dtos.cart;

import com.backend.ecommerce.dtos.user.UserDto;

import java.util.HashSet;
import java.util.UUID;

import static org.hibernate.validator.internal.util.CollectionHelper.newHashSet;


public record CartResponseDto(UUID cartId, UserDto user, HashSet<CartProductResponseDto> products) {
    public CartResponseDto() {
        this(null, null, newHashSet());
    }
}
