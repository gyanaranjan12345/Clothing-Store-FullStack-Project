package com.clothingstore.ClothingStore.controller;

import com.clothingstore.ClothingStore.repository.OrderRepository;
import com.clothingstore.ClothingStore.repository.ProductRepository;
import com.clothingstore.ClothingStore.repository.UserRepository;
import com.clothingstore.ClothingStore.entity.Order;

import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    public AdminController(
            ProductRepository productRepository,
            OrderRepository orderRepository,
            UserRepository userRepository) {

        this.productRepository =
                productRepository;

        this.orderRepository =
                orderRepository;

        this.userRepository =
                userRepository;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> dashboard() {

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "totalProducts",
                productRepository.count()
        );

        response.put(
                "totalOrders",
                orderRepository.count()
        );

        response.put(
                "totalCustomers",
                userRepository.count()
        );

        /*
         * For now revenue calculation
         * can be added using a repository
         * query based on delivered/paid orders.
         */

        response.put(
                "totalRevenue",
                BigDecimal.ZERO
        );

        return ResponseEntity.ok(
                response
        );
    }
    @GetMapping("/orders")
    public ResponseEntity<?> getOrders() {

        return ResponseEntity.ok(
                orderRepository.findAll()
        );
    }

    @PutMapping("/orders/{orderId}/status")
    public ResponseEntity<?> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody Map<String, String> request) {

        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found"
                        )
                );

        String status =
                request.get("status");

        order.setOrderStatus(
                Order.OrderStatus.valueOf(status)
        );

        return ResponseEntity.ok(
                orderRepository.save(order)
        );
    }
}