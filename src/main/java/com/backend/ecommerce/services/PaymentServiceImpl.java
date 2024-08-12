package com.backend.ecommerce.services;

import com.backend.ecommerce.entities.Payment;
import com.backend.ecommerce.entities.enums.PaymentStatus;
import com.backend.ecommerce.repositories.PaymentJpaRepo;
import com.backend.ecommerce.services.dtos.PaymentCreateDto;
import com.backend.ecommerce.services.dtos.PaymentResponseDto;
import com.backend.ecommerce.services.mappers.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
    private final PaymentJpaRepo paymentJpaRepo;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponseDto processPayment(PaymentCreateDto paymentCreateDto) {
        Payment payment = paymentMapper.toPayment(paymentCreateDto);
        Payment savedPayment = paymentJpaRepo.save(payment);
        return paymentMapper.toPaymentResponseDto(savedPayment);
    }

    @Override
    public PaymentResponseDto getPaymentDetails(UUID paymentId) {
        Payment payment = paymentJpaRepo.findById(paymentId).orElseThrow(() -> new IllegalArgumentException("Payment Not Found! "));
        return paymentMapper.toPaymentResponseDto(payment);
    }
}
