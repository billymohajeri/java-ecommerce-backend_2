package com.backend.ecommerce.controllers;

import com.backend.ecommerce.dtos.cart.CartDto;
import com.backend.ecommerce.dtos.cart.CartResponseDto;
import com.backend.ecommerce.services.CartServiceImpl;
import com.backend.ecommerce.shared.response.GlobalResponse;
import com.backend.ecommerce.shared.utilities.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

  @Autowired
  private CartServiceImpl cartService;
  @Autowired
  private SecurityUtils securityUtils;

  @PostMapping
  public ResponseEntity<GlobalResponse<CartResponseDto>> addProductToCart(@Valid @RequestBody CartDto cartDto) {
    return ResponseEntity.ok(new GlobalResponse<>(cartService.addProductToCart(cartDto), null));
  }

  @PreAuthorize("@securityUtils.isOwner(#userId)")
  @GetMapping("/{userId}")
  public ResponseEntity<GlobalResponse<CartResponseDto>> getCartByUserId(@PathVariable UUID userId) {
    CartResponseDto cartDto = cartService.getCartByUserId(userId);
    return ResponseEntity.ok(new GlobalResponse<>(cartDto, null));
  }

  
  @DeleteMapping("/{cartId}/product/{productId}")
  public void deleteProductFromCart(
          @PathVariable UUID cartId,
          @PathVariable UUID productId) {
    cartService.deleteProductFromCart(productId, cartId);
//    return ResponseEntity.ok("ok");
  }

}