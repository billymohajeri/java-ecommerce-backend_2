package com.backend.ecommerce.services;

import com.backend.ecommerce.entities.Order;
import com.backend.ecommerce.entities.Payment;
import com.backend.ecommerce.repositories.OrderJpaRepo;
import com.backend.ecommerce.repositories.PaymentJpaRepo;
import com.backend.ecommerce.dtos.payment.PaymentCreateDto;
import com.backend.ecommerce.dtos.payment.PaymentResponseDto;
import com.backend.ecommerce.services.interfaces.PaymentService;
import com.backend.ecommerce.mappers.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentJpaRepo paymentJpaRepo;
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private OrderJpaRepo orderJpaRepo;

    @Override
    public PaymentResponseDto processPayment(PaymentCreateDto paymentCreateDto) {

        boolean paymentExists = paymentJpaRepo.existsByOrderId(paymentCreateDto.orderId());
        if (paymentExists){
            throw new IllegalArgumentException("Payment already exists for this order");
        }

        Order order = orderJpaRepo.findById(paymentCreateDto.orderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        Payment payment = paymentMapper.toPayment(paymentCreateDto);
        payment.setOrder(order);

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
