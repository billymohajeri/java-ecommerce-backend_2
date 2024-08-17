package com.backend.ecommerce.controllers;

import com.backend.ecommerce.dtos.order.OrderCreateDto;
import com.backend.ecommerce.dtos.order.OrderUpdateDto;
import com.backend.ecommerce.services.interfaces.OrderService;
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
    public ResponseEntity<GlobalResponse<OrderCreateDto>> createOrder(@Valid @RequestBody OrderCreateDto orderDto){
        OrderCreateDto createdOrder = orderService.createOrder(orderDto);
        GlobalResponse<OrderCreateDto> response = new GlobalResponse<>(createdOrder,null);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<OrderCreateDto>> getOrderById(@PathVariable UUID id){
        Optional<OrderCreateDto> order = orderService.getOrderById(id);
        return order.map(dto -> ResponseEntity.ok(new GlobalResponse<>(dto, null))).orElseThrow();
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<OrderCreateDto>>> getAllOrders(){
        List<OrderCreateDto> orders = orderService.getAllOrders();
        GlobalResponse<List<OrderCreateDto>> response = new GlobalResponse<>(orders, null);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse<OrderCreateDto>> updateOrder(@PathVariable UUID id, @Valid @RequestBody OrderUpdateDto orderDto){
        OrderCreateDto updatedOrder = orderService.updateOrder(id, orderDto);
        GlobalResponse<OrderCreateDto> response = new GlobalResponse<>(updatedOrder, null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<OrderCreateDto>> deleteOrder(@PathVariable UUID id){
        OrderCreateDto deletedOrder = orderService.deleteOrder(id);
        GlobalResponse<OrderCreateDto> response = new GlobalResponse<>(deletedOrder, null);
        return ResponseEntity.ok(response);
    }
}
