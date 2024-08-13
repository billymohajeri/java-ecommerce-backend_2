package com.backend.ecommerce.mappers;

import com.backend.ecommerce.entities.Payment;
import com.backend.ecommerce.dtos.payment.PaymentCreateDto;
import com.backend.ecommerce.dtos.payment.PaymentResponseDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface PaymentMapper {

    Payment toPayment(PaymentCreateDto paymentCreateDto);

    PaymentResponseDto toPaymentResponseDto(Payment paymentEntity);
}
