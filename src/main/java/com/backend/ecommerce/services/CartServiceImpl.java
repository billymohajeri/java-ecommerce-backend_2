package com.backend.ecommerce.services;

import com.backend.ecommerce.dtos.cart.CartProductResponseDto;
import com.backend.ecommerce.dtos.cart.CartResponseDto;
import com.backend.ecommerce.dtos.user.UserDto;
import com.backend.ecommerce.entities.Cart;
import com.backend.ecommerce.entities.CartProduct;
import com.backend.ecommerce.entities.Product;
import com.backend.ecommerce.entities.User;
import com.backend.ecommerce.mappers.ProductMapper;
import com.backend.ecommerce.mappers.UserMapper;
import com.backend.ecommerce.repositories.CartJpaRepo;
import com.backend.ecommerce.dtos.cart.CartDto;
import com.backend.ecommerce.mappers.CartMapper;
import com.backend.ecommerce.repositories.CartProductJpaRepo;
import com.backend.ecommerce.repositories.ProductJpaRepo;
import com.backend.ecommerce.repositories.UserJpaRepo;
import com.backend.ecommerce.services.interfaces.CartService;
import com.backend.ecommerce.shared.exceptions.ErrorConstants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    CartJpaRepo cartJpaRepo;
    @Autowired
    ProductJpaRepo productJpaRepo;
    @Autowired
    CartProductJpaRepo cartProductJpaRepo;
    @Autowired
    UserJpaRepo userJpaRepo;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ProductMapper productMapper;

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

        cartProductJpaRepo.save(cartProduct);

        return getCartByUserId(user.getId());
    }

    public CartResponseDto getCartByUserId(UUID userId) {
        Cart cart = cartJpaRepo.findByUserId(userId).orElseThrow(() -> new NoSuchElementException(ErrorConstants.ErrorMessage.RESOURCE_NOT_FOUND));
        UserDto userDto = userMapper.toUserDto(cart.getUser());
        List<CartProduct> cartProducts = cartProductJpaRepo.findAllByCartId(cart.getId());
        HashSet<CartProductResponseDto> products = new HashSet<>();
        cartProducts.forEach(cp ->
                products.add(new CartProductResponseDto
                        (productMapper.toProductDto(cp.getProduct()), cp.getQuantity())
                ));
        return new CartResponseDto(cart.getId(), userDto, products);
    }
}
