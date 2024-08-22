package com.backend.ecommerce.services.interfaces;

import com.backend.ecommerce.dtos.ProductDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

  ProductDto createProduct(ProductDto productDto);

  List<ProductDto> getAllProducts(String search);

  Optional<ProductDto> getProductById(UUID id);

  ProductDto updateProduct(ProductDto productDto);

  ProductDto deleteProduct(UUID id);

  ProductDto patchProductStock(UUID id, int stock);
}
