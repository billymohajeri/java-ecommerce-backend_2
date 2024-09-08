package com.backend.ecommerce.services;

import com.backend.ecommerce.dtos.cart.CartDto;
import com.backend.ecommerce.dtos.cart.CartProductResponseDto;
import com.backend.ecommerce.dtos.cart.CartResponseDto;
import com.backend.ecommerce.dtos.user.UserDto;
import com.backend.ecommerce.entities.Cart;
import com.backend.ecommerce.entities.CartProduct;
import com.backend.ecommerce.entities.Product;
import com.backend.ecommerce.entities.User;
import com.backend.ecommerce.mappers.CartMapper;
import com.backend.ecommerce.mappers.ProductMapper;
import com.backend.ecommerce.mappers.UserMapper;
import com.backend.ecommerce.repositories.CartJpaRepo;
import com.backend.ecommerce.repositories.CartProductJpaRepo;
import com.backend.ecommerce.repositories.ProductJpaRepo;
import com.backend.ecommerce.repositories.UserJpaRepo;
import com.backend.ecommerce.services.interfaces.CartService;
import com.backend.ecommerce.shared.exceptions.ErrorConstants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
  @Autowired
  private CartMapper cartMapper;
  @Autowired
  private CartJpaRepo cartJpaRepo;
  @Autowired
  private ProductJpaRepo productJpaRepo;
  @Autowired
  private CartProductJpaRepo cartProductJpaRepo;
  @Autowired
  private UserJpaRepo userJpaRepo;
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private ProductMapper productMapper;

  @Transactional
  public CartResponseDto addProductToCart(CartDto cartDto) {
    User user = userJpaRepo.findById(cartDto.userId())
            .orElseThrow(() -> new NoSuchElementException(ErrorConstants.ErrorMessage.USER_DOES_NOT_EXIST));

    Product product = productJpaRepo.findById(cartDto.productId())
            .orElseThrow(() -> new NoSuchElementException(ErrorConstants.ErrorMessage.PRODUCT_DOES_NOT_EXIST));

    Optional<Cart> cart = cartJpaRepo.findByUserId(cartDto.userId());

    if (cart.isEmpty()) cart = Optional.of(cartJpaRepo.save(cartMapper.toCart(cartDto)));

    CartProduct cartProduct = cartProductJpaRepo.findByCartIdAndProductId(cart.get().getId(), product.getId())
            .orElse(new CartProduct());

    cartProduct.setCart(cart.get());
    cartProduct.setProduct(product);
    cartProduct.setQuantity(cartDto.quantity());

    // If quantity is 0, remove the CartProduct entity
    if (cartDto.quantity() == 0) {
      cartProductJpaRepo.delete(cartProduct);
    } else {
      cartProductJpaRepo.save(cartProduct);
    }

    return getCartByUserId(user.getId());
  }

  public CartResponseDto getCartByUserId(UUID userId) {
    Cart cart = cartJpaRepo.findByUserId(userId).orElse(new Cart());
    UserDto userDto = userMapper.toUserDto(cart.getUser());
    List<CartProduct> cartProducts = cartProductJpaRepo.findAllByCartId(cart.getId());
    Set<CartProductResponseDto> products = cartProducts.stream()
            .map(cp -> new CartProductResponseDto(
                    productMapper.toProductDto(cp.getProduct()),
                    cp.getQuantity()))
            .sorted(Comparator.comparing(cp -> cp.product().name()))  // Sort by product name
            .collect(Collectors.toCollection(LinkedHashSet::new)); // Preserve order
    return new CartResponseDto(cart.getId(), userDto, products);
  }

  public void deleteCartByUserId(UUID id) {
    Cart cart = cartJpaRepo.findByUserId(id).orElseThrow(() -> new NoSuchElementException(ErrorConstants.ErrorMessage.RESOURCE_NOT_FOUND));
    cartJpaRepo.delete(cart);
  }

  public void deleteProductFromCart(UUID productId, UUID cartId) {
    Cart cart = cartJpaRepo.findById(cartId)
            .orElseThrow(() -> new NoSuchElementException(ErrorConstants.ErrorMessage.RESOURCE_NOT_FOUND));

//    CartProduct productToRemove = cart.getCartProducts().stream()
//            .filter(product -> product.getId().equals(productId))
//            .findFirst()
//            .orElseThrow(() -> new NoSuchElementException("Pro
//
//            duct not found in the cart"));

    CartProduct cartProduct = cartProductJpaRepo.findByCartIdAndProductId(cartId, productId)
            .orElse(new CartProduct());

    cart.getCartProducts().remove(cartProduct);
    cartJpaRepo.save(cart);
  }
}