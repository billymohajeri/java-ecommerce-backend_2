package com.backend.ecommerce.services.interfaces;

import com.backend.ecommerce.entities.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

  public Product createProduct(Product product);

  public List<Product> getAllProducts();

  public Optional<Product> getProductById(UUID id);

  public Product updateProduct(Product product);

  public Product deleteProduct(UUID id);

  public Product patchProductStock(UUID id, int stock);
}
