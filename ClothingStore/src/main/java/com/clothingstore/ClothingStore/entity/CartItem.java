package com.clothingstore.ClothingStore.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")

public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


    @Column(nullable = false)
    private Integer quantity;


    @Column(nullable = false)
    private String size;


    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;


    // =========================
    // GETTERS & SETTERS
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(
            Product product) {

        this.product = product;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(
            Integer quantity) {

        this.quantity = quantity;
    }


    public String getSize() {
        return size;
    }

    public void setSize(
            String size) {

        this.size = size;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final CartItem item = new CartItem();

        public Builder cart(Cart cart) {
            item.setCart(cart);
            return this;
        }

        public Builder product(Product product) {
            item.setProduct(product);
            return this;
        }

        public Builder quantity(Integer quantity) {
            item.setQuantity(quantity);
            return this;
        }

        public Builder size(String size) {
            item.setSize(size);
            return this;
        }

        public Builder price(BigDecimal price) {
            item.setPrice(price);
            return this;
        }

        public CartItem build() {
            return item;
        }
    }
}