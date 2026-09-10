package com.clothingstore.ClothingStore.controller;

import com.clothingstore.ClothingStore.dto.*;
import com.clothingstore.ClothingStore.service.CartService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {

    private final CartService cartService;

    public CartController(
            CartService cartService) {

        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<CartResponse> getCart(
            Authentication authentication) {

        return ResponseEntity.ok(
                cartService.getCart(
                        authentication.getName()
                )
        );
    }

    @PostMapping("/items")
    public ResponseEntity<CartResponse> addToCart(
            Authentication authentication,
            @Valid @RequestBody AddToCartRequest request) {

        return ResponseEntity.ok(
                cartService.addToCart(
                        authentication.getName(),
                        request
                )
        );
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<CartResponse> updateCartItem(
            Authentication authentication,
            @PathVariable Long itemId,
            @Valid @RequestBody
            UpdateCartItemRequest request) {

        return ResponseEntity.ok(
                cartService.updateCartItem(
                        authentication.getName(),
                        itemId,
                        request
                )
        );
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<CartResponse> removeCartItem(
            Authentication authentication,
            @PathVariable Long itemId) {

        return ResponseEntity.ok(
                cartService.removeCartItem(
                        authentication.getName(),
                        itemId
                )
        );
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(
            Authentication authentication) {

        cartService.clearCart(
                authentication.getName()
        );

        return ResponseEntity.noContent().build();
    }
}