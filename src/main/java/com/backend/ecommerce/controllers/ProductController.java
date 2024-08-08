package com.backend.ecommerce.controllers;

import com.backend.ecommerce.entities.Product;
import com.backend.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
  @Autowired
  private ProductService productService;

  @PostMapping("")
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    return ResponseEntity.ok(productService.createProduct(product));
  }

  @GetMapping("")
  public ResponseEntity<List<Product>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
    Optional<Product> product = productService.getProductById(id);
    return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("")
  public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
    productService.updateProduct(product);
    return ResponseEntity.ok(product);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Product> deleteProduct(@PathVariable UUID id) {
    return ResponseEntity.ok(productService.deleteProduct(id));
  }
}
