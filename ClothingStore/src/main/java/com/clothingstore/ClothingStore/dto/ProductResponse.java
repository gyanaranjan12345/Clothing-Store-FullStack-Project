package com.clothingstore.ClothingStore.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stock;

    private String size;

    private String color;

    private String imageUrl;

    private Long categoryId;

    private String categoryName;
}