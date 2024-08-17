package com.backend.ecommerce.services;

import com.backend.ecommerce.dtos.order.OrderCreateDto;
import com.backend.ecommerce.dtos.order.OrderUpdateDto;
import com.backend.ecommerce.entities.Order;
import com.backend.ecommerce.mappers.OrderMapper;
import com.backend.ecommerce.repositories.OrderJpaRepo;
import com.backend.ecommerce.services.interfaces.OrderService;
import com.backend.ecommerce.shared.exceptions.ErrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderJpaRepo orderJpaRepo;

    @Autowired
    private OrderMapper orderMapper;

    public OrderCreateDto createOrder(OrderCreateDto orderDto) {
        Order order = orderMapper.toOrder(orderDto);
        Order savedOrder = orderJpaRepo.save(order);
        return orderMapper.toOrderDto(savedOrder);
    }

    public Optional<OrderCreateDto> getOrderById(UUID id){
        Optional<Order> order = orderJpaRepo.findById(id);
        return order.ofNullable(order.map(value -> orderMapper.toOrderDto(value))
                .orElseThrow(() -> new NoSuchElementException(ErrorConstants.ErrorMessage.ORDER_DOES_NOT_EXIST)));
    }

    public List<OrderCreateDto> getAllOrders(){
        List<Order> orders = orderJpaRepo.findAll();
        return orderMapper.toOrderDtos(orders);
    }

    public OrderCreateDto updateOrder(UUID id, OrderUpdateDto orderDto){
        return orderJpaRepo.findById(id)
                .map(order -> {
                    order.setAddress(orderDto.address());
                    order.setComments(orderDto.comments());
                    order.setStatus(orderDto.status());
                    Order updatedOrder = orderJpaRepo.save(order);
                    return orderMapper.toOrderDto(updatedOrder);
                }).orElseThrow(() -> new NoSuchElementException(ErrorConstants.ErrorMessage.ORDER_DOES_NOT_EXIST));
    }

    public OrderCreateDto deleteOrder(UUID id){
        Order order = orderJpaRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ErrorConstants.ErrorMessage.ORDER_DOES_NOT_EXIST));

        orderJpaRepo.deleteById(id);

        return orderMapper.toOrderDto(order);
    }

}
