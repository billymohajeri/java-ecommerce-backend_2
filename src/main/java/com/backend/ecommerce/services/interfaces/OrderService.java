package com.backend.ecommerce.services.interfaces;

import com.backend.ecommerce.dtos.order.OrderCreateDto;
import com.backend.ecommerce.dtos.order.OrderUpdateDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    OrderCreateDto createOrder(OrderCreateDto orderDto);
    Optional<OrderCreateDto> getOrderById(UUID id);
    List<OrderCreateDto> getAllOrders();
    OrderCreateDto updateOrder(UUID id, OrderUpdateDto orderDto);
    void deleteOrder(UUID id);
}
