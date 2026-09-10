package com.clothingstore.ClothingStore.controller;

import com.clothingstore.ClothingStore.dto.WishlistResponse;
import com.clothingstore.ClothingStore.service.WishlistService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin(origins = "http://localhost:4200")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(
            WishlistService wishlistService) {

        this.wishlistService = wishlistService;
    }

    @GetMapping
    public ResponseEntity<WishlistResponse> getWishlist(
            Authentication authentication) {

        return ResponseEntity.ok(
                wishlistService.getWishlist(
                        authentication.getName()
                )
        );
    }

    @PostMapping("/{productId}")
    public ResponseEntity<WishlistResponse>
    addToWishlist(
            Authentication authentication,
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                wishlistService.addToWishlist(
                        authentication.getName(),
                        productId
                )
        );
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<WishlistResponse>
    removeFromWishlist(
            Authentication authentication,
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                wishlistService.removeFromWishlist(
                        authentication.getName(),
                        productId
                )
        );
    }
}