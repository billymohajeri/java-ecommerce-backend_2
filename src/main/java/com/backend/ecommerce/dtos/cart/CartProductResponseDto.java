package com.backend.ecommerce.dtos.cart;

import com.backend.ecommerce.dtos.ProductDto;

public record CartProductResponseDto(ProductDto product, int quantity) {
}
