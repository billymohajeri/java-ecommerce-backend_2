package com.backend.ecommerce.mappers;

import com.backend.ecommerce.dtos.cart.CartDto;
import com.backend.ecommerce.entities.Cart;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface CartMapper {

  @Mapping(target = "user.id", source = "userId")
  Cart toCart(CartDto source);

  @Mapping(target = "userId", source = "user.id")
  CartDto toCartDto(Cart source);
}