package com.backend.ecommerce.services;

import com.backend.ecommerce.entities.Product;
import com.backend.ecommerce.repositories.ProductJpaRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl {
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
    Optional<Product> findProduct = productJpaRepo.findById((product.getId()));
    if (findProduct.isPresent()) {
      return productJpaRepo.save(product);
    }
    return product;
  }

  public Product deleteProduct(UUID id) {
    Optional<Product> findProduct = productJpaRepo.findById(id);
    if (findProduct.isPresent()) {
      Product deletedProduct = findProduct.get();
      Hibernate.initialize(deletedProduct.getImages());
      productJpaRepo.delete(deletedProduct);
      return deletedProduct;
    }
    return null;
  }

  public Product patchProductStock(UUID id, int stock) {
    Optional<Product> findProduct = productJpaRepo.findById(id);
    if (findProduct.isPresent()) {
      Product patchedProduct = findProduct.get();
      patchedProduct.setStock(stock);
      productJpaRepo.save(patchedProduct);
      return patchedProduct;
    }
    return null;
  }
}
