package com.backend.ecommerce.services.interfaces;

import com.backend.ecommerce.dtos.cart.CartDto;
import com.backend.ecommerce.dtos.cart.CartResponseDto;
import com.backend.ecommerce.entities.Cart;

import java.util.UUID;

public interface CartService {
    Cart create(CartDto cartDto);
    CartResponseDto getCartByUserId(UUID userId);
}
