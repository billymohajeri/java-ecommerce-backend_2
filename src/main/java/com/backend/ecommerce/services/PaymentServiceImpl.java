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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentJpaRepo paymentJpaRepo;
    @Autowired
    private PaymentMapper paymentMapper;

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

    @Override
    public List<PaymentResponseDto> getAllPayments() {
        List<Payment> payments = paymentJpaRepo.findAll();
        return payments.stream()
                .map(paymentMapper::toPaymentResponseDto)
                .collect(Collectors.toList());
    }
}
