package com.clothingstore.ClothingStore.service;

import com.clothingstore.ClothingStore.dto.*;
import com.clothingstore.ClothingStore.entity.*;
import com.clothingstore.ClothingStore.exception.ResourceNotFoundException;
import com.clothingstore.ClothingStore.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            ProductRepository productRepository,
            UserRepository userRepository) {

        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public CartResponse getCart(String email) {

        User user = getUser(email);

        Cart cart = getOrCreateCart(user);

        return convertToResponse(cart);
    }

    public CartResponse addToCart(
            String email,
            AddToCartRequest request) {

        User user = getUser(email);

        Product product = productRepository
                .findById(request.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id: "
                                        + request.getProductId()
                        )
                );

        if (product.getStock() <= 0) {

            throw new RuntimeException(
                    "Product is out of stock"
            );
        }

        Cart cart = getOrCreateCart(user);

        CartItem cartItem =
                cartItemRepository
                        .findByCartIdAndProductId(
                                cart.getId(),
                                product.getId()
                        )
                        .orElse(null);

        int newQuantity;

        if (cartItem == null) {

            if (request.getQuantity() > product.getStock()) {

                throw new RuntimeException(
                        "Requested quantity exceeds available stock"
                );
            }

            cartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.getQuantity())
                    .price(product.getPrice())
                    .build();

            cart.getItems().add(cartItem);

        } else {

            newQuantity =
                    cartItem.getQuantity()
                            + request.getQuantity();

            if (newQuantity > product.getStock()) {

                throw new RuntimeException(
                        "Requested quantity exceeds available stock"
                );
            }

            cartItem.setQuantity(newQuantity);
        }

        cartRepository.save(cart);

        return convertToResponse(cart);
    }

    public CartResponse updateCartItem(
            String email,
            Long itemId,
            UpdateCartItemRequest request) {

        User user = getUser(email);

        Cart cart = getOrCreateCart(user);

        CartItem cartItem =
                cartItemRepository.findById(itemId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Cart item not found with id: "
                                                + itemId
                                )
                        );

        if (!cartItem.getCart().getId()
                .equals(cart.getId())) {

            throw new RuntimeException(
                    "Cart item does not belong to this user"
            );
        }

        Product product = cartItem.getProduct();

        if (request.getQuantity() > product.getStock()) {

            throw new RuntimeException(
                    "Requested quantity exceeds available stock"
            );
        }

        cartItem.setQuantity(
                request.getQuantity()
        );

        cartItemRepository.save(cartItem);

        return convertToResponse(cart);
    }

    public CartResponse removeCartItem(
            String email,
            Long itemId) {

        User user = getUser(email);

        Cart cart = getOrCreateCart(user);

        CartItem cartItem =
                cartItemRepository.findById(itemId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Cart item not found with id: "
                                                + itemId
                                )
                        );

        if (!cartItem.getCart().getId()
                .equals(cart.getId())) {

            throw new RuntimeException(
                    "Cart item does not belong to this user"
            );
        }

        cart.getItems().remove(cartItem);

        cartItemRepository.delete(cartItem);

        return convertToResponse(cart);
    }

    public void clearCart(String email) {

        User user = getUser(email);

        Cart cart = getOrCreateCart(user);

        cart.getItems().clear();

        cartRepository.save(cart);
    }

    private User getUser(String email) {

        return userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        )
                );
    }

    private Cart getOrCreateCart(User user) {

        return cartRepository
                .findByUserId(user.getId())
                .orElseGet(() -> {

                    Cart cart = Cart.builder()
                            .user(user)
                            .build();

                    return cartRepository.save(cart);
                });
    }

    private CartResponse convertToResponse(
            Cart cart) {

        List<CartItemResponse> items =
                cart.getItems()
                        .stream()
                        .map(item -> {

                            BigDecimal subtotal =
                                    item.getPrice()
                                            .multiply(
                                                    BigDecimal.valueOf(
                                                            item.getQuantity()
                                                    )
                                            );

                            return CartItemResponse.builder()
                                    .id(item.getId())
                                    .productId(
                                            item.getProduct().getId()
                                    )
                                    .productName(
                                            item.getProduct().getName()
                                    )
                                    .imageUrl(
                                            item.getProduct()
                                                    .getImageUrl()
                                    )
                                    .price(item.getPrice())
                                    .quantity(
                                            item.getQuantity()
                                    )
                                    .subtotal(subtotal)
                                    .build();
                        })
                        .toList();

        BigDecimal totalAmount =
                items.stream()
                        .map(CartItemResponse::getSubtotal)
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );

        int totalItems =
                items.stream()
                        .mapToInt(
                                CartItemResponse::getQuantity
                        )
                        .sum();

        return CartResponse.builder()
                .cartId(cart.getId())
                .items(items)
                .totalAmount(totalAmount)
                .totalItems(totalItems)
                .build();
    }
}