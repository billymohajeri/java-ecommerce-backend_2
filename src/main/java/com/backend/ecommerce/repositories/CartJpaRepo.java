package com.backend.ecommerce.repositories;

import com.backend.ecommerce.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartJpaRepo extends JpaRepository<Cart, UUID> {

  Optional<Cart> findByUserId(UUID userId);
}