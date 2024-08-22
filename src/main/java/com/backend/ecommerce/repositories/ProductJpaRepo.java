package com.backend.ecommerce.repositories;

import com.backend.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductJpaRepo extends JpaRepository<Product, UUID> {
  List<Product> findByNameContainingIgnoreCase(String name);
}
