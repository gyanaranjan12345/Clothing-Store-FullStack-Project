package com.clothingstore.ClothingStore.dto;

import com.clothingstore.ClothingStore.entity.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderResponse {

    private Long orderId;

    private BigDecimal totalAmount;

    private String shippingAddress;

    private String phoneNumber;

    private Order.OrderStatus orderStatus;

    private PaymentStatus paymentStatus;

    private LocalDateTime orderDate;

    private List<OrderItemResponse> items;
}