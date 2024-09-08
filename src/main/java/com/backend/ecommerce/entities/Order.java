package com.backend.ecommerce.entities;

import com.backend.ecommerce.entities.enums.OrderStatus;
import com.backend.ecommerce.shared.exceptions.ErrorConstants;
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
import java.util.List;
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

  @NotNull(message = ErrorConstants.ErrorMessage.USER_ID_REQUIRED)
  @Column(name = "user_id", nullable = false)
  private UUID userId;

  @NotNull(message = ErrorConstants.ErrorMessage.ORDER_DATE_DOES_NOT_EXIST)
  @PastOrPresent(message = ErrorConstants.ErrorMessage.ORDER_DATE_LIMIT)
  @Column(name = "date_time", nullable = false)
  private LocalDateTime dateTime;

  @Column(name = "comments")
  private String comments;

  @NotNull(message = ErrorConstants.ErrorMessage.ORDER_STATUS_DOES_NOT_EXIST)
  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private OrderStatus status;

  @NotBlank(message = ErrorConstants.ErrorMessage.ORDER_ADDRESS_DOES_NOT_EXIST)
  @Size(max = 255, message = ErrorConstants.ErrorMessage.ORDER_ADDRESS_LIMIT)
  @Column(name = "address")
  private String address;

  @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
  private Payment payment;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "order_product",
          schema = "ecomapp",
          joinColumns = @JoinColumn(name = "order_id"),
          inverseJoinColumns = @JoinColumn(name = "product_id"))
  private List<Product> products;
}
