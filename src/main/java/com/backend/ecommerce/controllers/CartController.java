package com.backend.ecommerce.controllers;

import com.backend.ecommerce.entities.Cart;
import com.backend.ecommerce.services.CartService;
import com.backend.ecommerce.services.dtos.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("")
    public ResponseEntity<Cart> createCart(@RequestBody CartDto cartDto){
        return ResponseEntity.ok(cartService.create(cartDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable UUID userId){
        Optional<Cart> cartDto = cartService.getCartByUserId(userId);
        return cartDto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
