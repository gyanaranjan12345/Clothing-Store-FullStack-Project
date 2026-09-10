package com.clothingstore.ClothingStore.repository;

import com.clothingstore.ClothingStore.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    Optional<Product> findByNameIgnoreCase(String name);

    @Query("""
        SELECT p
        FROM Product p
        WHERE
            (:keyword IS NULL OR
             LOWER(p.name)
             LIKE LOWER(CONCAT('%', :keyword, '%')))
        AND
            (:categoryId IS NULL OR
             p.category.id = :categoryId)
        AND
            (:minPrice IS NULL OR
             p.price >= :minPrice)
        AND
            (:maxPrice IS NULL OR
             p.price <= :maxPrice)
        AND
            (:size IS NULL OR
             p.size = :size)
        AND
            (:color IS NULL OR
             p.color = :color)
        """)
    List<Product> searchProducts(

            @Param("keyword")
            String keyword,

            @Param("categoryId")
            Long categoryId,

            @Param("minPrice")
            BigDecimal minPrice,

            @Param("maxPrice")
            BigDecimal maxPrice,

            @Param("size")
            String size,

            @Param("color")
            String color
    );
}