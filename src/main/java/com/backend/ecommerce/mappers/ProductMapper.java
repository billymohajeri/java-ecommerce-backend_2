package com.backend.ecommerce.mappers;

import com.backend.ecommerce.dtos.ProductDto;
import com.backend.ecommerce.entities.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface ProductMapper {

  Product toProduct(ProductDto source);

  ProductDto toProductDto(Product source);

  List<ProductDto> toProductDtos(List<Product> products);
}
