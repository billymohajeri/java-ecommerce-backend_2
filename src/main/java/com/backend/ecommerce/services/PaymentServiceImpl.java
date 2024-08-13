package com.backend.ecommerce.services;

import com.backend.ecommerce.entities.Payment;
import com.backend.ecommerce.repositories.PaymentJpaRepo;
import com.backend.ecommerce.dtos.payment.PaymentCreateDto;
import com.backend.ecommerce.dtos.payment.PaymentResponseDto;
import com.backend.ecommerce.services.interfaces.PaymentService;
import com.backend.ecommerce.mappers.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentJpaRepo paymentJpaRepo;
    @Autowired
    PaymentMapper paymentMapper;

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
