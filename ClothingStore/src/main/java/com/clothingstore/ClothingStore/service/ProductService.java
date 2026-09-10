package com.clothingstore.ClothingStore.service;

import com.clothingstore.ClothingStore.entity.Product;
import com.clothingstore.ClothingStore.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(
            ProductRepository productRepository) {

        this.productRepository =
                productRepository;
    }

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public Product getProductById(Long id) {

        return productRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found"
                        )
                );
    }

    public Product createProduct(
            Product product) {

        return productRepository.save(product);
    }

    public Product updateProduct(
            Long id,
            Product product) {

        Product existingProduct =
                getProductById(id);

        existingProduct.setName(
                product.getName()
        );

        existingProduct.setDescription(
                product.getDescription()
        );

        existingProduct.setPrice(
                product.getPrice()
        );

        existingProduct.setStock(
                product.getStock()
        );

        existingProduct.setSize(
                product.getSize()
        );

        existingProduct.setColor(
                product.getColor()
        );

        existingProduct.setImageUrl(
                product.getImageUrl()
        );

        existingProduct.setCategory(
                product.getCategory()
        );

        return productRepository.save(
                existingProduct
        );
    }

    public void deleteProduct(Long id) {

        if (!productRepository.existsById(id)) {

            throw new RuntimeException(
                    "Product not found"
            );
        }

        productRepository.deleteById(id);
    }

    public List<Product> searchProducts(

            String keyword,

            Long categoryId,

            BigDecimal minPrice,

            BigDecimal maxPrice,

            String size,

            String color) {

        return productRepository.searchProducts(

                keyword,

                categoryId,

                minPrice,

                maxPrice,

                size,

                color
        );
    }
}