package com.backend.ecommerce.controllers;

import com.backend.ecommerce.services.PaymentServiceImpl;
import com.backend.ecommerce.dtos.payment.PaymentCreateDto;
import com.backend.ecommerce.dtos.payment.PaymentResponseDto;
import com.backend.ecommerce.services.interfaces.PaymentService;
import com.backend.ecommerce.shared.response.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<GlobalResponse<PaymentResponseDto>> processPayment(@Valid @RequestBody PaymentCreateDto paymentCreateDto){
        PaymentResponseDto response = paymentService.processPayment(paymentCreateDto);
        return new ResponseEntity<>(new GlobalResponse<>(response,null), HttpStatus.CREATED);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<GlobalResponse<PaymentResponseDto>> getPaymentDetails(@PathVariable UUID paymentId) {
        PaymentResponseDto response = paymentService.getPaymentDetails(paymentId);
        return ResponseEntity.ok(new GlobalResponse<>(response,null));
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<PaymentResponseDto>>> getAllPayments() {
        List<PaymentResponseDto> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(new GlobalResponse<>(payments, null));
    }
}
