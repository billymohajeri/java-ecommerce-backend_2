package com.backend.ecommerce.mappers;

import com.backend.ecommerce.entities.Payment;
import com.backend.ecommerce.dtos.payment.PaymentCreateDto;
import com.backend.ecommerce.dtos.payment.PaymentResponseDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface PaymentMapper {
    @Mapping(target = "order", ignore = true)
    Payment toPayment(PaymentCreateDto paymentCreateDto);
    @Mapping(source = "order.id", target = "orderId")
    PaymentResponseDto toPaymentResponseDto(Payment paymentEntity);
}
