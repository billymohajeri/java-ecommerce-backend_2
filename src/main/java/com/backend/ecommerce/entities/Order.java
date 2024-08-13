package com.backend.ecommerce.entities;

import com.backend.ecommerce.entities.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "order", schema = "ecomapp")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "User id is required!")
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @NotNull(message = "Date and time are required")
    @PastOrPresent(message = "Date and time must be in the past or present")
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "comments")
    private String comments;

    @NotNull(message = "Order status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address should not exceed 255 characters")
    @Column(name = "address")
    private String address;
}
