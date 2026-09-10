package com.clothingstore.ClothingStore.controller;

import com.clothingstore.ClothingStore.entity.Product;
import com.clothingstore.ClothingStore.service.ProductService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(
        origins = "http://localhost:4200"
)
public class ProductController {

    private final ProductService productService;

    public ProductController(
            ProductService productService) {

        this.productService =
                productService;
    }

    // GET ALL PRODUCTS

    @GetMapping
    public ResponseEntity<List<Product>>
    getAllProducts() {

        return ResponseEntity.ok(
                productService.getAllProducts()
        );
    }

    // GET PRODUCT BY ID

    @GetMapping("/{id}")
    public ResponseEntity<Product>
    getProductById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                productService.getProductById(id)
        );
    }

    // SEARCH + FILTER

    @GetMapping("/search")
    public ResponseEntity<List<Product>>
    searchProducts(

            @RequestParam(
                    required = false
            )
            String keyword,

            @RequestParam(
                    required = false
            )
            Long categoryId,

            @RequestParam(
                    required = false
            )
            BigDecimal minPrice,

            @RequestParam(
                    required = false
            )
            BigDecimal maxPrice,

            @RequestParam(
                    required = false
            )
            String size,

            @RequestParam(
                    required = false
            )
            String color) {

        List<Product> products =
                productService.searchProducts(

                        keyword,

                        categoryId,

                        minPrice,

                        maxPrice,

                        size,

                        color
                );

        return ResponseEntity.ok(products);
    }

    // CREATE PRODUCT

    @PostMapping
    public ResponseEntity<Product>
    createProduct(
            @RequestBody Product product) {

        return ResponseEntity.ok(
                productService.createProduct(
                        product
                )
        );
    }

    // UPDATE PRODUCT

    @PutMapping("/{id}")
    public ResponseEntity<Product>
    updateProduct(

            @PathVariable Long id,

            @RequestBody Product product) {

        return ResponseEntity.ok(
                productService.updateProduct(
                        id,
                        product
                )
        );
    }

    // DELETE PRODUCT

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        return ResponseEntity.noContent()
                .build();
    }
}