package com.backend.ecommerce.services;

import com.backend.ecommerce.entities.Cart;
import com.backend.ecommerce.repositories.CartJpaRepo;
import com.backend.ecommerce.services.dtos.CartDto;
import com.backend.ecommerce.services.mappers.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    CartJpaRepo cartJpaRepo;

    public Cart create(CartDto cartDto) {
        return cartJpaRepo.save(cartMapper.toCart(cartDto));
    }

    public Optional<Cart> getCartByUserId(UUID userId) {
        return cartJpaRepo.findByUserId(userId);
//        map(value -> cartMapper.toCartDto(value));
    }
}
