package com.backend.ecommerce.controllers;

import com.backend.ecommerce.dtos.order.OrderDto;
import com.backend.ecommerce.dtos.order.OrderUpdateDto;
import com.backend.ecommerce.services.interfaces.OrderService;
import com.backend.ecommerce.shared.exceptions.CustomException;
import com.backend.ecommerce.shared.response.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<GlobalResponse<OrderDto>> createOrder(@Valid @RequestBody OrderDto orderDto){
        OrderDto createdOrder = orderService.createOrder(orderDto);
        GlobalResponse<OrderDto> response = new GlobalResponse<>(createdOrder,null);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<OrderDto>> getOrderById(@PathVariable UUID id){
        Optional<OrderDto> order = orderService.getOrderById(id);
        return order.map(dto -> ResponseEntity.ok(new GlobalResponse<>(dto, null)))
                .orElseThrow(() -> new CustomException("Order not found! ", HttpStatus.NOT_FOUND.value()));
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<OrderDto>>> getAllOrders(){
        List<OrderDto> orders = orderService.getAllOrders();
        GlobalResponse<List<OrderDto>> response = new GlobalResponse<>(orders, null);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse<OrderDto>> updateOrder(@PathVariable UUID id, @Valid @RequestBody OrderUpdateDto orderDto){
        try{
            OrderDto updatedOrder = orderService.updateOrder(id, orderDto);
            GlobalResponse<OrderDto> response = new GlobalResponse<>(updatedOrder, null);
            return ResponseEntity.ok(response);
        }catch (RuntimeException e){
            throw new CustomException("Order not found", HttpStatus.NOT_FOUND.value());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<OrderDto>> deleteOrder(@PathVariable UUID id){
        orderService.deleteOrder(id);
        GlobalResponse<Void> response = new GlobalResponse<>(null, null);
        return ResponseEntity.noContent().build();
    }
}
