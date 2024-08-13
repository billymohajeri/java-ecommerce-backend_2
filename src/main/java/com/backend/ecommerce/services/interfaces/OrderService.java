package com.backend.ecommerce.services.interfaces;

import com.backend.ecommerce.dtos.order.OrderDto;
import com.backend.ecommerce.dtos.order.OrderUpdateDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    Optional<OrderDto> getOrderById(UUID id);
    List<OrderDto> getAllOrders();
    OrderDto updateOrder(UUID id, OrderUpdateDto orderDto);
    void deleteOrder(UUID id);
}
