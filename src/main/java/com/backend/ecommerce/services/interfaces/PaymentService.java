package com.backend.ecommerce.services.interfaces;

import com.backend.ecommerce.dtos.payment.PaymentCreateDto;
import com.backend.ecommerce.dtos.payment.PaymentResponseDto;
import com.backend.ecommerce.dtos.payment.PaymentUpdateDto;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    PaymentResponseDto processPayment(PaymentCreateDto paymentCreateDto);
    PaymentResponseDto getPaymentDetails(UUID paymentId);
    List<PaymentResponseDto> getAllPayments();
    PaymentResponseDto updatePaymentDetails(UUID paymentId, PaymentUpdateDto paymentUpdateDto);
}
