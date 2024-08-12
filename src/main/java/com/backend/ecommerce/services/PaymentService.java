package com.backend.ecommerce.services;

import com.backend.ecommerce.services.dtos.PaymentCreateDto;
import com.backend.ecommerce.services.dtos.PaymentResponseDto;

import java.util.UUID;

public interface PaymentService {
    PaymentResponseDto processPayment(PaymentCreateDto paymentCreateDto);
    PaymentResponseDto getPaymentDetails(UUID paymentId);
}
