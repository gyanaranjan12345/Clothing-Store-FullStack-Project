package com.clothingstore.ClothingStore.config;

import com.clothingstore.ClothingStore.entity.Category;
import com.clothingstore.ClothingStore.entity.Product;
import com.clothingstore.ClothingStore.repository.CategoryRepository;
import com.clothingstore.ClothingStore.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class CatalogDataInitializer {

    @Bean
    CommandLineRunner seedCatalog(
            CategoryRepository categoryRepository,
            ProductRepository productRepository) {

        return args -> {
            Category men = categoryRepository
                    .findByNameIgnoreCase("Men")
                    .orElseGet(() -> categoryRepository.save(Category.builder()
                            .name("Men")
                            .description("Everyday and occasion wear for men")
                            .build()));

            Category women = categoryRepository
                    .findByNameIgnoreCase("Women")
                    .orElseGet(() -> categoryRepository.save(Category.builder()
                            .name("Women")
                            .description("Modern styles for every occasion")
                            .build()));

            Category kids = categoryRepository
                    .findByNameIgnoreCase("Kids")
                    .orElseGet(() -> categoryRepository.save(Category.builder()
                            .name("Kids")
                            .description("Comfortable styles for kids")
                            .build()));

            seedProduct(productRepository, Product.builder()
                    .name("Men Cotton T-Shirt")
                    .description("Premium cotton regular fit T-shirt")
                    .price(new BigDecimal("799.00"))
                    .stock(50)
                    .size("L")
                    .color("Black")
                    .imageUrl("https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcQJDJ7EELtya_ca8rWh2PxOAFcA8D4QJke5qPdsg-cFBHAljRYciQJELN_-SCzZF1C7CnyJioMnl8CZuJ6-lPQKYj2X4HAn_ipPyAw42yDiX6jF4lnBrqzmyg")
                    .category(men)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Premium Black Hoodie")
                    .description("Premium cotton hoodie")
                    .price(new BigDecimal("1499.00"))
                    .stock(25)
                    .size("XL")
                    .color("Black")
                    .imageUrl("https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcQaujD1Bwy9yugS0Dt2HZ9BKFEHyrAjqCQe5dgGzUHl960aHHbE34nIp8p5JU8mi5RHCxxlqF4fVwT3QsZkeLBirVchwyJJ")
                    .category(men)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Classic Oxford Shirt")
                    .description("A versatile cotton shirt with a clean button-down finish.")
                    .price(new BigDecimal("39.99"))
                    .stock(40)
                    .size("M")
                    .color("White")
                    .imageUrl("https://images.unsplash.com/photo-1603252110481-7ba9a3a7f2d2?auto=format&fit=crop&w=800&q=80")
                    .category(men)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Relaxed Denim Jacket")
                    .description("A durable denim layer designed for everyday outfits.")
                    .price(new BigDecimal("64.99"))
                    .stock(25)
                    .size("L")
                    .color("Blue")
                    .imageUrl("https://images.unsplash.com/photo-1551028719-00167b16eac5?auto=format&fit=crop&w=800&q=80")
                    .category(men)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Tapered Chino Trousers")
                    .description("Stretch cotton chinos with a polished tapered silhouette.")
                    .price(new BigDecimal("49.99"))
                    .stock(35)
                    .size("32")
                    .color("Olive")
                    .imageUrl("https://images.unsplash.com/photo-1624378439575-d8705ad7ae80?auto=format&fit=crop&w=800&q=80")
                    .category(men)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Slim Fit Polo Shirt")
                    .description("Breathable piqué polo shirt with a smart casual fit.")
                    .price(new BigDecimal("899.00"))
                    .stock(32)
                    .size("M")
                    .color("Navy")
                    .imageUrl("https://images.unsplash.com/photo-1625910513413-5fc45a5b0e87?auto=format&fit=crop&w=800&q=80")
                    .category(men)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Textured Casual Blazer")
                    .description("Lightweight textured blazer for polished everyday dressing.")
                    .price(new BigDecimal("2199.00"))
                    .stock(18)
                    .size("L")
                    .color("Beige")
                    .imageUrl("https://images.unsplash.com/photo-1507679799987-c73779587ccf?auto=format&fit=crop&w=800&q=80")
                    .category(men)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Straight Fit Denim Jeans")
                    .description("Classic five-pocket denim with a comfortable straight fit.")
                    .price(new BigDecimal("1299.00"))
                    .stock(30)
                    .size("32")
                    .color("Indigo")
                    .imageUrl("https://images.unsplash.com/photo-1542272604-787c3835535d?auto=format&fit=crop&w=800&q=80")
                    .category(men)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Lightweight Bomber Jacket")
                    .description("A versatile zip-front jacket with a clean modern shape.")
                    .price(new BigDecimal("1799.00"))
                    .stock(20)
                    .size("XL")
                    .color("Black")
                    .imageUrl("https://images.unsplash.com/photo-1591047139829-d91aecb6caea?auto=format&fit=crop&w=800&q=80")
                    .category(men)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Cotton Cargo Joggers")
                    .description("Soft cotton joggers with utility pockets and an elastic waist.")
                    .price(new BigDecimal("1099.00"))
                    .stock(36)
                    .size("L")
                    .color("Charcoal")
                    .imageUrl("https://images.unsplash.com/photo-1552902865-b72c031ac5ea?auto=format&fit=crop&w=800&q=80")
                    .category(men)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Linen Wrap Dress")
                    .description("A breathable linen dress with an easy wrap shape.")
                    .price(new BigDecimal("59.99"))
                    .stock(30)
                    .size("M")
                    .color("Terracotta")
                    .imageUrl("https://images.unsplash.com/photo-1515372039744-b8f02a3ae446?auto=format&fit=crop&w=800&q=80")
                    .category(women)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Ribbed Knit Cardigan")
                    .description("A soft textured cardigan for lightweight layering.")
                    .price(new BigDecimal("44.99"))
                    .stock(28)
                    .size("S")
                    .color("Cream")
                    .imageUrl("https://images.unsplash.com/photo-1591369822096-ffd140ec948f?auto=format&fit=crop&w=800&q=80")
                    .category(women)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Wide Leg Trousers")
                    .description("Flowing high-rise trousers with a comfortable elastic back.")
                    .price(new BigDecimal("54.99"))
                    .stock(22)
                    .size("M")
                    .color("Black")
                    .imageUrl("https://images.unsplash.com/photo-1594633312681-425c7b97ccd1?auto=format&fit=crop&w=800&q=80")
                    .category(women)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Satin Slip Skirt")
                    .description("A fluid satin skirt with an elegant midi length.")
                    .price(new BigDecimal("1199.00"))
                    .stock(24)
                    .size("M")
                    .color("Rose")
                    .imageUrl("https://images.unsplash.com/photo-1583496661160-fb5886a0aaaa?auto=format&fit=crop&w=800&q=80")
                    .category(women)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Relaxed Cotton Blouse")
                    .description("A soft cotton blouse with a relaxed drape and easy fit.")
                    .price(new BigDecimal("899.00"))
                    .stock(34)
                    .size("L")
                    .color("Blue")
                    .imageUrl("https://images.unsplash.com/photo-1605763240000-7e93b172d754?auto=format&fit=crop&w=800&q=80")
                    .category(women)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Tailored Culotte Pants")
                    .description("Crisp wide-leg culottes with a comfortable tailored waist.")
                    .price(new BigDecimal("1399.00"))
                    .stock(20)
                    .size("M")
                    .color("Black")
                    .imageUrl("https://images.unsplash.com/photo-1506629905607-d9c297d3e7d3?auto=format&fit=crop&w=800&q=80")
                    .category(women)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Cropped Denim Jacket")
                    .description("A fresh cropped denim layer with subtle vintage character.")
                    .price(new BigDecimal("1599.00"))
                    .stock(21)
                    .size("S")
                    .color("Light Blue")
                    .imageUrl("https://images.unsplash.com/photo-1544022613-e87ca75a784a?auto=format&fit=crop&w=800&q=80")
                    .category(women)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Floral Midi Skirt")
                    .description("A printed midi skirt made for effortless day-to-evening style.")
                    .price(new BigDecimal("1099.00"))
                    .stock(26)
                    .size("M")
                    .color("Multi")
                    .imageUrl("https://images.unsplash.com/photo-1551488831-00ddcb6c6bd3?auto=format&fit=crop&w=800&q=80")
                    .category(women)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Soft Jersey Jumpsuit")
                    .description("An easy one-piece jersey jumpsuit with a flattering fit.")
                    .price(new BigDecimal("1499.00"))
                    .stock(19)
                    .size("L")
                    .color("Olive")
                    .imageUrl("https://images.unsplash.com/photo-1529139574466-a303027c1d8b?auto=format&fit=crop&w=800&q=80")
                    .category(women)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Oversized Lounge Sweater")
                    .description("A cozy brushed sweater for relaxed weekends and travel.")
                    .price(new BigDecimal("999.00"))
                    .stock(29)
                    .size("M")
                    .color("Grey")
                    .imageUrl("https://images.unsplash.com/photo-1434389677669-e08b4cac3105?auto=format&fit=crop&w=800&q=80")
                    .category(women)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Color Block Hoodie")
                    .description("A cozy cotton-blend hoodie made for active days.")
                    .price(new BigDecimal("29.99"))
                    .stock(45)
                    .size("8Y")
                    .color("Green")
                    .imageUrl("https://images.unsplash.com/photo-1519238263530-99bdd11df2ea?auto=format&fit=crop&w=800&q=80")
                    .category(kids)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Everyday Graphic Tee")
                    .description("A playful soft jersey tee for school and weekends.")
                    .price(new BigDecimal("18.99"))
                    .stock(50)
                    .size("6Y")
                    .color("Yellow")
                    .imageUrl("https://images.unsplash.com/photo-1503919545889-aef636e10ad4?auto=format&fit=crop&w=800&q=80")
                    .category(kids)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Canvas Utility Shorts")
                    .description("Hard-wearing pull-on shorts with practical pockets.")
                    .price(new BigDecimal("24.99"))
                    .stock(38)
                    .size("10Y")
                    .color("Navy")
                    .imageUrl("https://images.unsplash.com/photo-1506629905607-d9c297d3e7d3?auto=format&fit=crop&w=800&q=80")
                    .category(kids)
                    .build());

                        seedProduct(productRepository, Product.builder()
                                        .name("Kids Polo Shirt")
                                        .description("Soft cotton polo shirt for active days")
                                        .price(new BigDecimal("599.00"))
                                        .stock(35)
                                        .size("8Y")
                                        .color("Blue")
                                        .imageUrl("https://images.unsplash.com/photo-1519457431-44ccd64a579b?auto=format&fit=crop&w=800&q=80")
                                        .category(kids)
                                        .build());

            seedProduct(productRepository, Product.builder()
                    .name("Kids Denim Overalls")
                    .description("Durable denim overalls with adjustable shoulder straps.")
                    .price(new BigDecimal("899.00"))
                    .stock(24)
                    .size("8Y")
                    .color("Blue")
                    .imageUrl("https://images.unsplash.com/photo-1519457431-44ccd64a579b?auto=format&fit=crop&w=800&q=80")
                    .category(kids)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Kids Zip Track Jacket")
                    .description("A lightweight track jacket for playtime and outdoor adventures.")
                    .price(new BigDecimal("799.00"))
                    .stock(31)
                    .size("10Y")
                    .color("Red")
                    .imageUrl("https://images.unsplash.com/photo-1503919545889-aef636e10ad4?auto=format&fit=crop&w=800&q=80")
                    .category(kids)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Kids Printed Dress")
                    .description("A cheerful printed dress in soft, easy-care cotton.")
                    .price(new BigDecimal("749.00"))
                    .stock(27)
                    .size("6Y")
                    .color("Pink")
                    .imageUrl("https://images.unsplash.com/photo-1518831959646-742c3a14ebf7?auto=format&fit=crop&w=800&q=80")
                    .category(kids)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Kids Fleece Sweatpants")
                    .description("Warm fleece sweatpants with a flexible elastic waistband.")
                    .price(new BigDecimal("649.00"))
                    .stock(40)
                    .size("8Y")
                    .color("Grey")
                    .imageUrl("https://images.unsplash.com/photo-1519238263530-99bdd11df2ea?auto=format&fit=crop&w=800&q=80")
                    .category(kids)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Kids Canvas Sneakers")
                    .description("Lightweight canvas sneakers with a comfortable everyday sole.")
                    .price(new BigDecimal("999.00"))
                    .stock(23)
                    .size("2Y")
                    .color("White")
                    .imageUrl("https://images.unsplash.com/photo-1514989940723-e8e51635b782?auto=format&fit=crop&w=800&q=80")
                    .category(kids)
                    .build());

            seedProduct(productRepository, Product.builder()
                    .name("Kids Rainproof Windbreaker")
                    .description("A bright water-resistant layer for changing weather.")
                    .price(new BigDecimal("1099.00"))
                    .stock(18)
                    .size("10Y")
                    .color("Yellow")
                    .imageUrl("https://images.unsplash.com/photo-1503919545889-aef636e10ad4?auto=format&fit=crop&w=800&q=80")
                    .category(kids)
                    .build());
        };
    }

        private void seedProduct(
                        ProductRepository productRepository,
                        Product product) {

                productRepository.findByNameIgnoreCase(product.getName())
                                .orElseGet(() -> productRepository.save(product));
        }
}