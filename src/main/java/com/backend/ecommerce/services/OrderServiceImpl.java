package com.backend.ecommerce.services;

import com.backend.ecommerce.dtos.order.OrderDto;
import com.backend.ecommerce.dtos.order.OrderUpdateDto;
import com.backend.ecommerce.entities.Order;
import com.backend.ecommerce.mappers.OrderMapper;
import com.backend.ecommerce.repositories.OrderJpaRepo;
import com.backend.ecommerce.services.interfaces.OrderService;
import com.backend.ecommerce.shared.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
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

    public OrderDto updateOrder(UUID id, OrderUpdateDto orderDto){
        return orderJpaRepo.findById(id)
                .map(order -> {
                    order.setAddress(orderDto.address());
                    order.setComments(orderDto.comments());
                    order.setStatus(orderDto.status());
                    Order updatedOrder = orderJpaRepo.save(order);
                    return orderMapper.toOrderDto(updatedOrder);
                }).orElseThrow(() -> new CustomException("Order not found", HttpStatus.NOT_FOUND.value()));
    }

    public void deleteOrder(UUID id){
        Optional<Order> deletedOrder = orderJpaRepo.findById(id);
        if(deletedOrder.isPresent()){
            orderJpaRepo.deleteById(id);
        }else{
            throw new CustomException("Order not found", HttpStatus.NOT_FOUND.value());
        }
    }

}
