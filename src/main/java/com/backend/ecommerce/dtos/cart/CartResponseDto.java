package com.backend.ecommerce.dtos.cart;

import com.backend.ecommerce.dtos.user.UserDto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.hibernate.validator.internal.util.CollectionHelper.newHashSet;


public record CartResponseDto(UUID cartId, UserDto user, Set<CartProductResponseDto> products) {
    public CartResponseDto() {
        this(null, null, newHashSet());
    }
}
