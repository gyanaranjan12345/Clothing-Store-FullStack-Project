package com.clothingstore.ClothingStore.repository;

import com.clothingstore.ClothingStore.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository
        extends JpaRepository<OrderItem, Long> {
}