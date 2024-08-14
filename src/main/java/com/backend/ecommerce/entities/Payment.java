package com.backend.ecommerce.entities;

import com.backend.ecommerce.entities.enums.PaymentMethod;
import com.backend.ecommerce.entities.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "payment", schema = "ecomapp")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
    @NotNull(message = "Amount cannot be null")
    @Column(name = "amount", nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment status cannot be null")
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment method cannot be null")
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod method;
}
