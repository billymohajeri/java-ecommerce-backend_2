package com.backend.ecommerce.services.mappers;

import com.backend.ecommerce.entities.Cart;
import com.backend.ecommerce.services.dtos.CartDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, uses = UserMapper.class)
public interface CartMapper {

    @Mapping(target = "user.id", source = "userId")
    Cart toCart(CartDto source);

    @Mapping(target = "userId", source = "user.id")
    CartDto toCartDto(Cart source);

}
