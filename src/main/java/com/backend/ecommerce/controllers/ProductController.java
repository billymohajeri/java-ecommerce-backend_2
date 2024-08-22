package com.backend.ecommerce.controllers;

import com.backend.ecommerce.dtos.ProductDto;
import com.backend.ecommerce.services.interfaces.ProductService;
import com.backend.ecommerce.shared.response.GlobalResponse;
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

  @PostMapping
  public ResponseEntity<GlobalResponse<ProductDto>> createProduct(@RequestBody ProductDto productDto) {
    return ResponseEntity.ok(new GlobalResponse<>(productService.createProduct(productDto), null));
  }

  @GetMapping
  public ResponseEntity<GlobalResponse<List<ProductDto>>> getAllProducts(
          @RequestParam(required = false) String search) {
    List<ProductDto> products = productService.getAllProducts(search);
    GlobalResponse<List<ProductDto>> response = new GlobalResponse<>(products, null);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GlobalResponse<ProductDto>> getProductById(@PathVariable UUID id) {
    Optional<ProductDto> productDto = productService.getProductById(id);
    return productDto.map(dto -> ResponseEntity.ok(new GlobalResponse<>(dto, null)))
            .orElseThrow();
  }

  @PutMapping
  public ResponseEntity<GlobalResponse<ProductDto>> updateProduct(@RequestBody ProductDto productDto) {
    return ResponseEntity.ok(new GlobalResponse<>(productService.updateProduct(productDto), null));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<GlobalResponse<ProductDto>> deleteProduct(@PathVariable UUID id) {
    GlobalResponse<ProductDto> response = new GlobalResponse<>(productService.deleteProduct(id), null);
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GlobalResponse<ProductDto>> patchProductStock(@PathVariable UUID id, @RequestBody int stock) {
    GlobalResponse<ProductDto> response = new GlobalResponse<>(productService.patchProductStock(id, stock), null);
    return ResponseEntity.ok(response);
  }


}
