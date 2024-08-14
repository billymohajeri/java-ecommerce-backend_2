package com.backend.ecommerce.repositories;

import com.backend.ecommerce.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PaymentJpaRepo extends JpaRepository<Payment, UUID> {
    @Query("SELECT COUNT(p) > 0 FROM Payment p WHERE p.order.id = :orderId")
    boolean existsByOrderId(@Param("orderId") UUID orderId);
}
