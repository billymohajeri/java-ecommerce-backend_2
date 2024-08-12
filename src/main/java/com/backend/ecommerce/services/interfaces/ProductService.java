package com.backend.ecommerce.services.interfaces;

import com.backend.ecommerce.dtos.ProductDto;
import com.backend.ecommerce.entities.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

  ProductDto createProduct(ProductDto productDto);

  List<ProductDto> getAllProducts();

  Optional<ProductDto> getProductById(UUID id);

  Product updateProduct(Product product);

  Product deleteProduct(UUID id);

  Product patchProductStock(UUID id, int stock);
}
