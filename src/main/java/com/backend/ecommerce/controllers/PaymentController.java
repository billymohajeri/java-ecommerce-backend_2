package com.backend.ecommerce.controllers;

import com.backend.ecommerce.services.PaymentServiceImpl;
import com.backend.ecommerce.dtos.payment.PaymentCreateDto;
import com.backend.ecommerce.dtos.payment.PaymentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {
    @Autowired
    PaymentServiceImpl paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDto> processPayment(@RequestBody PaymentCreateDto paymentCreateDto){
        PaymentResponseDto response = paymentService.processPayment(paymentCreateDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponseDto> getPaymentDetails(@PathVariable UUID paymentId) {
        PaymentResponseDto response = paymentService.getPaymentDetails(paymentId);
        return ResponseEntity.ok(response);
    }
}
