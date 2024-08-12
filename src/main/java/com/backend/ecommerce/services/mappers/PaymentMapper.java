package com.backend.ecommerce.services.mappers;

import com.backend.ecommerce.entities.Payment;
import com.backend.ecommerce.services.dtos.PaymentCreateDto;
import com.backend.ecommerce.services.dtos.PaymentResponseDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface PaymentMapper {

    Payment toPayment(PaymentCreateDto paymentCreateDto);

    PaymentResponseDto toPaymentResponseDto(Payment paymentEntity);
}
