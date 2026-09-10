package com.clothingstore.ClothingStore.service;

import com.clothingstore.ClothingStore.dto.CheckoutRequest;
import com.clothingstore.ClothingStore.dto.OrderItemResponse;
import com.clothingstore.ClothingStore.dto.OrderResponse;
import com.clothingstore.ClothingStore.entity.Cart;
import com.clothingstore.ClothingStore.entity.CartItem;
import com.clothingstore.ClothingStore.entity.Order;
import com.clothingstore.ClothingStore.entity.OrderItem;
import com.clothingstore.ClothingStore.entity.Product;
import com.clothingstore.ClothingStore.entity.User;
import com.clothingstore.ClothingStore.exception.ResourceNotFoundException;
import com.clothingstore.ClothingStore.repository.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    public OrderService(
            CartRepository cartRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository,
            UserRepository userRepository) {

        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public Order placeOrder(
            Long userId,
            CheckoutRequest request) {

        User user =
                userRepository.findById(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );

        Cart cart =
                cartRepository
                        .findByUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Cart is empty"
                                )
                        );

        if (cart.getItems().isEmpty()) {

            throw new RuntimeException(
                    "Cart is empty"
            );
        }

        Order order = new Order();

        order.setUser(user);

        order.setFullName(
                request.getFullName()
        );

        order.setPhoneNumber(
                request.getPhoneNumber()
        );

        order.setShippingAddress(
                request.getAddressLine1()
                        + ", "
                        + request.getCity()
                        + ", "
                        + request.getState()
                        + " - "
                        + request.getPincode()
        );

        order.setPaymentMethod(
                request.getPaymentMethod()
        );

        order.setOrderStatus(
                Order.OrderStatus.PLACED
        );

        BigDecimal total =
                BigDecimal.ZERO;

        for (CartItem cartItem :
                cart.getItems()) {

            Product product =
                    productRepository
                            .findById(
                                    cartItem
                                            .getProduct()
                                            .getId()
                            )
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Product not found"
                                    )
                            );

            if (
                    product.getStock()
                            < cartItem.getQuantity()
            ) {

                throw new RuntimeException(
                        product.getName()
                                + " is out of stock"
                );
            }

            BigDecimal itemTotal =
                    product.getPrice()
                            .multiply(
                                    BigDecimal.valueOf(
                                            cartItem.getQuantity()
                                    )
                            );

            OrderItem orderItem =
                    new OrderItem();

            orderItem.setOrder(order);

            orderItem.setProduct(product);

            orderItem.setQuantity(
                    cartItem.getQuantity()
            );

            orderItem.setPrice(
                    product.getPrice()
            );

            orderItem.setSize(
                    cartItem.getSize()
            );

            orderItem.setSubtotal(
                    itemTotal
            );

            order.getItems()
                    .add(orderItem);

            total = total.add(
                    itemTotal
            );

            product.setStock(
                    product.getStock()
                            - cartItem.getQuantity()
            );

            productRepository.save(product);
        }

        order.setTotalAmount(total);

        Order savedOrder =
                orderRepository.save(order);

        cart.getItems().clear();

        cartRepository.save(cart);

        return savedOrder;
    }
}