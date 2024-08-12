package com.backend.ecommerce.services;

import com.backend.ecommerce.dtos.ProductDto;
import com.backend.ecommerce.entities.Product;
import com.backend.ecommerce.mappers.ProductMapper;
import com.backend.ecommerce.repositories.ProductJpaRepo;
import com.backend.ecommerce.services.interfaces.ProductService;
import com.backend.ecommerce.shared.exceptions.ErrorConstants;
import org.hibernate.Hibernate;
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

  public List<ProductDto> getAllProducts() {
    List<Product> products = productJpaRepo.findAll();
    return productMapper.toProductDtos(products);
  }

  public Optional<ProductDto> getProductById(UUID id) {
    Optional<Product> product = productJpaRepo.findById(id);
    return Optional.ofNullable(
            product.map(value -> productMapper.toProductDto(value))
                    .orElseThrow(() -> new NoSuchElementException(
                            ErrorConstants.ErrorMessage.PRODUCT_DOES_NOT_EXIST)));
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
