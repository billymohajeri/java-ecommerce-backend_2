package com.backend.ecommerce.repositories;

import com.backend.ecommerce.entities.Cart;
import com.backend.ecommerce.entities.CartProduct;
import com.backend.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartProductJpaRepo extends JpaRepository<CartProduct, UUID> {

    List<CartProduct> findAllByCartId(UUID id);

    Optional<CartProduct> findByCartIdAndProductId(UUID id, UUID id1);
}
