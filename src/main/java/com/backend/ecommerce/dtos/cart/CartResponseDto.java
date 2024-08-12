package com.backend.ecommerce.dtos.cart;

import com.backend.ecommerce.entities.Product;
import com.backend.ecommerce.entities.User;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

//TODO: cartDto will have products as well
public record CartResponseDto(UUID cartId, User user, List<Product> products) {
}
