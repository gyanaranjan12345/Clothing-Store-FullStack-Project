package com.clothingstore.ClothingStore.service;

import com.clothingstore.ClothingStore.dto.ProductResponse;
import com.clothingstore.ClothingStore.dto.WishlistResponse;
import com.clothingstore.ClothingStore.entity.Product;
import com.clothingstore.ClothingStore.entity.User;
import com.clothingstore.ClothingStore.entity.Wishlist;
import com.clothingstore.ClothingStore.exception.ResourceNotFoundException;
import com.clothingstore.ClothingStore.repository.ProductRepository;
import com.clothingstore.ClothingStore.repository.UserRepository;
import com.clothingstore.ClothingStore.repository.WishlistRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public WishlistService(
            WishlistRepository wishlistRepository,
            UserRepository userRepository,
            ProductRepository productRepository) {

        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public WishlistResponse getWishlist(
            String email) {

        User user = getUser(email);

        Wishlist wishlist =
                getOrCreateWishlist(user);

        return convertToResponse(wishlist);
    }

    public WishlistResponse addToWishlist(
            String email,
            Long productId) {

        User user = getUser(email);

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Product not found with id: "
                                                + productId
                                )
                        );

        Wishlist wishlist =
                getOrCreateWishlist(user);

        wishlist.getProducts().add(product);

        wishlistRepository.save(wishlist);

        return convertToResponse(wishlist);
    }

    public WishlistResponse removeFromWishlist(
            String email,
            Long productId) {

        User user = getUser(email);

        Wishlist wishlist =
                getOrCreateWishlist(user);

        wishlist.getProducts()
                .removeIf(product ->
                        product.getId().equals(productId)
                );

        wishlistRepository.save(wishlist);

        return convertToResponse(wishlist);
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

    private Wishlist getOrCreateWishlist(
            User user) {

        return wishlistRepository
                .findByUserId(user.getId())
                .orElseGet(() -> {

                    Wishlist wishlist =
                            Wishlist.builder()
                                    .user(user)
                                    .build();

                    return wishlistRepository.save(
                            wishlist
                    );
                });
    }

    private WishlistResponse convertToResponse(
            Wishlist wishlist) {

        List<ProductResponse> products =
                wishlist.getProducts()
                        .stream()
                        .map(product ->
                                ProductResponse.builder()
                                        .id(product.getId())
                                        .name(product.getName())
                                        .description(
                                                product.getDescription()
                                        )
                                        .price(product.getPrice())
                                        .stock(product.getStock())
                                        .size(product.getSize())
                                        .color(product.getColor())
                                        .imageUrl(
                                                product.getImageUrl()
                                        )
                                        .categoryId(
                                                product.getCategory()
                                                        != null
                                                        ? product.getCategory()
                                                        .getId()
                                                        : null
                                        )
                                        .categoryName(
                                                product.getCategory()
                                                        != null
                                                        ? product.getCategory()
                                                        .getName()
                                                        : null
                                        )
                                        .build()
                        )
                        .toList();

        return WishlistResponse.builder()
                .wishlistId(wishlist.getId())
                .products(products)
                .build();
    }
}