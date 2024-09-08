package com.backend.ecommerce.mappers;

import com.backend.ecommerce.dtos.order.OrderCreateDto;
import com.backend.ecommerce.entities.Order;
import com.backend.ecommerce.shared.utilities.Constants;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface OrderMapper {

  @Mapping(source = "dateTime", target = "dateTime", dateFormat = Constants.SERVER_DATE_FORMAT)
  Order toOrder(OrderCreateDto source);

  @Mapping(source = "dateTime", target = "dateTime", dateFormat = Constants.SERVER_DATE_FORMAT)
  OrderCreateDto toOrderDto(Order source);

  List<OrderCreateDto> toOrderDtos(List<Order> orders);

  List<Order> toOrders(List<OrderCreateDto> orderDtos);
}