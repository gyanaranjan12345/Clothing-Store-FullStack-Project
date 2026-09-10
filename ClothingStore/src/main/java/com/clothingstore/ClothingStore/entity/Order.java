package com.clothingstore.ClothingStore.entity;

import com.clothingstore.ClothingStore.dto.PaymentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Column(nullable = false)
    private String fullName;


    @Column(nullable = false)
    private String phoneNumber;


    @Column(nullable = false, length = 500)
    private String shippingAddress;


    @Column(nullable = false)
    private String paymentMethod;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;


    @Column(nullable = false)
    private BigDecimal totalAmount;


    @Column(nullable = false)
    private LocalDateTime createdAt;


    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItem> items =
            new ArrayList<>();


    // =========================
    // CONSTRUCTORS
    // =========================

    public Order() {
        this.createdAt = LocalDateTime.now();
    }


    // =========================
    // GETTERS & SETTERS
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(
            String shippingAddress) {

        this.shippingAddress =
                shippingAddress;
    }


    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(
            String paymentMethod) {

        this.paymentMethod =
                paymentMethod;
    }


    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(
            OrderStatus orderStatus) {

        this.orderStatus =
                orderStatus;
    }


    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(
            BigDecimal totalAmount) {

        this.totalAmount =
                totalAmount;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(
            LocalDateTime createdAt) {

        this.createdAt =
                createdAt;
    }


    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(
            List<OrderItem> items) {

        this.items = items;
    }


    // =========================
    // ENUM
    // =========================

    public enum OrderStatus {

        PLACED,

        CONFIRMED,

        SHIPPED,

        OUT_FOR_DELIVERY,

        DELIVERED,

        CANCELLED
    }
}