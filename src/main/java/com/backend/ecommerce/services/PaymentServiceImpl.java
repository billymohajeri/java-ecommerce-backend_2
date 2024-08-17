package com.backend.ecommerce.services;

import com.backend.ecommerce.dtos.payment.PaymentUpdateDto;
import com.backend.ecommerce.entities.Order;
import com.backend.ecommerce.entities.Payment;
import com.backend.ecommerce.repositories.OrderJpaRepo;
import com.backend.ecommerce.repositories.PaymentJpaRepo;
import com.backend.ecommerce.dtos.payment.PaymentCreateDto;
import com.backend.ecommerce.dtos.payment.PaymentResponseDto;
import com.backend.ecommerce.services.interfaces.PaymentService;
import com.backend.ecommerce.mappers.PaymentMapper;
import com.backend.ecommerce.shared.exceptions.ErrorConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
            throw new NoSuchElementException(ErrorConstants.ErrorMessage.PAYMENT_ALREADY_EXIST);
        }

        Order order = orderJpaRepo.findById(paymentCreateDto.orderId())
                .orElseThrow(() -> new NoSuchElementException(ErrorConstants.ErrorMessage.ORDER_DOES_NOT_EXIST));

        Payment payment = paymentMapper.toPayment(paymentCreateDto);
        payment.setOrder(order);

        Payment savedPayment = paymentJpaRepo.save(payment);
        return paymentMapper.toPaymentResponseDto(savedPayment);
    }

    @Override
    public PaymentResponseDto getPaymentDetails(UUID paymentId) {
        Payment payment = paymentJpaRepo.findById(paymentId).orElseThrow(() -> new NoSuchElementException(ErrorConstants.ErrorMessage.PAYMENT_DOES_NOT_EXIST));
        return paymentMapper.toPaymentResponseDto(payment);
    }

    @Override
    public List<PaymentResponseDto> getAllPayments() {
        List<Payment> payments = paymentJpaRepo.findAll();
        return paymentMapper.toPaymentResponseDtoList(payments);
    }

    @Override
    public PaymentResponseDto updatePaymentDetails(UUID paymentId, PaymentUpdateDto paymentUpdateDto){
        Payment payment = paymentJpaRepo.findById(paymentId)
                .orElseThrow(() -> new NoSuchElementException(ErrorConstants.ErrorMessage.PAYMENT_DOES_NOT_EXIST));
        if (paymentUpdateDto.amount() != null){
            payment.setAmount(paymentUpdateDto.amount());
        }
        if(paymentUpdateDto.method()!= null){
            payment.setMethod(paymentUpdateDto.method());
        }
        if(paymentUpdateDto.status() != null){
            payment.setStatus(paymentUpdateDto.status());
        }

        Payment updatedPayment = paymentJpaRepo.save(payment);
        return paymentMapper.toPaymentResponseDto(updatedPayment);
    }
}
