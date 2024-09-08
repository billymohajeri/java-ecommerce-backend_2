package com.backend.ecommerce.services.interfaces;

import com.backend.ecommerce.dtos.cart.CartDto;
import com.backend.ecommerce.dtos.cart.CartResponseDto;

import java.util.UUID;

public interface CartService {
  CartResponseDto addProductToCart(CartDto cartDto);

  CartResponseDto getCartByUserId(UUID userId);

  void deleteCartByUserId(UUID id);
}