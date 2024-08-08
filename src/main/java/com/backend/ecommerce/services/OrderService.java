package com.backend.ecommerce.services;

import com.backend.ecommerce.entities.Order;
import com.backend.ecommerce.repositories.OrderJpaRepo;
import com.backend.ecommerce.services.dtos.OrderDto;
import com.backend.ecommerce.services.mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    OrderJpaRepo orderJpaRepo;

    @Autowired
    OrderMapper orderMapper;

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderMapper.toOrder(orderDto);
        Order savedOrder = orderJpaRepo.save(order);
        return orderMapper.toOrderDto(savedOrder);
    }

    public Optional<OrderDto> getOrderById(UUID id){
        return orderJpaRepo.findById(id).map(orderMapper::toOrderDto);
    }

    public List<OrderDto> getAllOrders(){
        List<Order> orders = orderJpaRepo.findAll();
        return orderMapper.toOrderDtos(orders);
    }
}
