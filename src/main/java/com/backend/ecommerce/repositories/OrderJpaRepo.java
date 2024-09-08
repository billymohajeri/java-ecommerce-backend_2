package com.backend.ecommerce.repositories;

import com.backend.ecommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderJpaRepo extends JpaRepository<Order, UUID> {

  List<Order> findByUserId(UUID userId);
}