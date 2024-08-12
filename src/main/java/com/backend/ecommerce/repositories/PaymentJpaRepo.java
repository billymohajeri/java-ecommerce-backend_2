package com.backend.ecommerce.repositories;

import com.backend.ecommerce.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentJpaRepo extends JpaRepository<Payment, UUID> {

}
