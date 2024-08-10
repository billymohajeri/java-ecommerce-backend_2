package com.backend.ecommerce.services;

import com.backend.ecommerce.dtos.cart.CartResponseDto;
import com.backend.ecommerce.entities.Cart;
import com.backend.ecommerce.repositories.CartJpaRepo;
import com.backend.ecommerce.dtos.cart.CartDto;
import com.backend.ecommerce.mappers.CartMapper;
import com.backend.ecommerce.services.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    CartJpaRepo cartJpaRepo;

    public Cart create(CartDto cartDto) {
        return cartJpaRepo.save(cartMapper.toCart(cartDto));
    }

    public CartResponseDto getCartByUserId(UUID userId) {
        return cartJpaRepo.findByUserId(userId).map(cartMapper::toCartResponseDto).orElseThrow();
    }
}
