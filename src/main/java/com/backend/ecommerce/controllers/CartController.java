package com.backend.ecommerce.controllers;

import com.backend.ecommerce.dtos.cart.CartResponseDto;
import com.backend.ecommerce.entities.Cart;
import com.backend.ecommerce.services.CartServiceImpl;
import com.backend.ecommerce.dtos.cart.CartDto;
import com.backend.ecommerce.shared.response.GlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    @Autowired
    CartServiceImpl cartServiceImpl;

    @PostMapping
    public ResponseEntity<GlobalResponse<Cart>> createCart(@RequestBody CartDto cartDto) {
        return ResponseEntity.ok(new GlobalResponse<>(cartServiceImpl.create(cartDto), null));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GlobalResponse<CartResponseDto>> getCartByUserId(@PathVariable UUID userId) {
        CartResponseDto cartDto = cartServiceImpl.getCartByUserId(userId);
        return ResponseEntity.ok(new GlobalResponse<>(cartDto, null));
    }

}
