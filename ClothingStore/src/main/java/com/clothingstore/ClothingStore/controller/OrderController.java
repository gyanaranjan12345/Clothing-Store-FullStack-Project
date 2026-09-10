package com.clothingstore.ClothingStore.controller;

import com.clothingstore.ClothingStore.dto.CheckoutRequest;
import com.clothingstore.ClothingStore.entity.Order;
import com.clothingstore.ClothingStore.repository.OrderRepository;
import com.clothingstore.ClothingStore.service.OrderService;
import org.springframework.http.ResponseEntity;


import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public OrderController(
            OrderService orderService , OrderRepository orderRepository) {

        this.orderService =
                orderService;
        this.orderRepository=orderRepository;
    }

    @PostMapping
    public ResponseEntity<?> placeOrder(
            @RequestBody CheckoutRequest request,
            Authentication authentication) {

        /*
         * Get logged-in user's ID
         * from your JWT/authentication
         * implementation.
         */

        Long userId =
                Long.valueOf(
                        authentication.getName()
                );

        Order order =
                orderService.placeOrder(
                        userId,
                        request
                );

        return ResponseEntity.ok(order);
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyOrders(
            Authentication authentication) {

        Long userId =
                Long.valueOf(
                        authentication.getName()
                );

        return ResponseEntity.ok(
                orderRepository
                        .findByUserId(userId)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(
            @PathVariable Long id,
            Authentication authentication) {

        Order order =
                orderRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Order not found"
                                )
                        );

        return ResponseEntity.ok(order);
    }
}