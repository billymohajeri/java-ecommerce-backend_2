package com.backend.ecommerce.services;

import com.backend.ecommerce.entities.Product;
import com.backend.ecommerce.repositories.ProductJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
  @Autowired
  ProductJpaRepo productJpaRepo;

  public Product createProduct(Product product) {
    return productJpaRepo.save(product);
  }

  public List<Product> getAllProducts() {
    return productJpaRepo.findAll();
  }

  public Optional<Product> getProductById(UUID id) {
    return productJpaRepo.findById(id);
  }

  public Product updateProduct(Product product) {
    Optional<Product> findProduct = productJpaRepo.findById(product.getId());
    if (findProduct.isPresent()) {
      return productJpaRepo.save(product);
    }
    return product;
  }

  public Optional<Product> deleteProduct(UUID id) {
    Optional<Product> findProduct = productJpaRepo.findById(id);
    if (findProduct.isPresent()) {
      productJpaRepo.delete(findProduct.get());
      return findProduct;
    }
    return Optional.empty();
  }

//  public Product patchProductStock(Product product){
//    Optional<Product> findProduct = productJpaRepo.findById(product.getId());
//    if(findProduct.isPresent()){
//      productJpaRepo.
//    }
//  }
}
