package com.backend.ecommerce.repositories;

import com.backend.ecommerce.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PaymentJpaRepo extends JpaRepository<Payment, UUID> {
    boolean existsByOrder_Id(UUID orderId);
}
