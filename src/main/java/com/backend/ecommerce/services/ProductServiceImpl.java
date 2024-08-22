package com.backend.ecommerce.services;

import com.backend.ecommerce.dtos.ProductDto;
import com.backend.ecommerce.entities.Product;
import com.backend.ecommerce.mappers.ProductMapper;
import com.backend.ecommerce.repositories.ProductJpaRepo;
import com.backend.ecommerce.services.interfaces.ProductService;
import com.backend.ecommerce.shared.exceptions.ErrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductJpaRepo productJpaRepo;
  @Autowired
  private ProductMapper productMapper;

  public ProductDto createProduct(ProductDto productDto) {
    Product product = productMapper.toProduct(productDto);
    Product savedProduct = productJpaRepo.save(product);
    return productMapper.toProductDto(savedProduct);
  }

  public List<ProductDto> getAllProducts(String search) {
    List<Product> products;

    if (search != null && !search.isEmpty()) {
      products = productJpaRepo.findByNameContainingIgnoreCase(search);
    } else {
      products = productJpaRepo.findAll();
    }
    return productMapper.toProductDtos(products);
  }

  public Optional<ProductDto> getProductById(UUID id) {
    Optional<Product> product = productJpaRepo.findById(id);
    return Optional.ofNullable(
            product.map(value -> productMapper.toProductDto(value))
                    .orElseThrow(() -> new NoSuchElementException(
                            ErrorConstants.ErrorMessage.PRODUCT_DOES_NOT_EXIST)));
  }

  public ProductDto updateProduct(ProductDto productDto) {
    Optional<ProductDto> findProductDto = getProductById(productDto.id());
    return findProductDto.map(dto -> {
      Product updatedProduct = productMapper.toProduct(productDto);
      Product newProduct = productJpaRepo.save(updatedProduct);
      return productMapper.toProductDto(newProduct);
    }).orElseThrow(() -> new NoSuchElementException(
            ErrorConstants.ErrorMessage.PRODUCT_DOES_NOT_EXIST));
  }

  public ProductDto deleteProduct(UUID id) {
    Optional<ProductDto> findProductDto = getProductById(id);
    return findProductDto.map(dto -> {
      Product deletedProduct = productMapper.toProduct(dto);
      productJpaRepo.delete(deletedProduct);
      return dto;
    }).orElseThrow(() -> new NoSuchElementException(
            ErrorConstants.ErrorMessage.PRODUCT_DOES_NOT_EXIST));
  }

  public ProductDto patchProductStock(UUID id, int stock) {
    Optional<Product> findProduct = productJpaRepo.findById(id);
    if (findProduct.isPresent()) {
      Product patchedProduct = findProduct.get();
      patchedProduct.setStock(stock);
      productJpaRepo.save(patchedProduct);
      return productMapper.toProductDto(patchedProduct);
    } else throw new NoSuchElementException(ErrorConstants.ErrorMessage.PRODUCT_DOES_NOT_EXIST);
  }
}
