# Clothing-Store-FullStack-Project
Full-stack Clothing Store e-commerce application built using Java, Spring Boot, Spring Security, JWT, REST APIs, MySQL, and Angular. Includes user authentication, product and category management, product search, cart, wishlist, checkout, order management, stock validation, exception handling, API validation and a responsive Angular frontend.

# Clothing Store - Full Stack E-Commerce Application

A full-stack Clothing Store e-commerce application developed using *Java, Spring Boot, Angular, and MySQL*.

The application provides a complete online shopping experience including user authentication, product management, product search, cart, wishlist, checkout, order management, stock validation, and API documentation.



## 🚀 Technologies Used

### Backend
- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- REST APIs
- MySQL
- Maven
- SLF4J Logging
- Bean Validation

### Frontend
- Angular
- TypeScript
- HTML5
- CSS3
- Bootstrap
- Angular Services
- Angular Routing
- HTTP Client

### Tools
- IntelliJ IDEA / Spring Tool Suite
- MySQL Workbench
- Postman
- Git
- GitHub


## 📁 Project Structure

Clothing-Store-FullStack-Project
│
├── ClothingStore
│   ├── src
│   ├── pom.xml
│   └── ...
│
├── clothing-store-ui
│   ├── src
│   ├── package.json
│   ├── angular.json
│   └── ...
│
├── README.md
└── .gitignore


✨ Features

👤 User Management
   User registration
   User login
   JWT-based authentication
   Secure password handling
   Role-based authorization

🛍️ Product Management
   Add products
   Update products
   Delete products
   View product details
   Product listing
   Product search
   Category-based products
   Stock management

🛒 Shopping Cart
   Add products to cart
   Update quantity
   Remove products
   View cart
   Calculate total price
   Stock validation

❤️ Wishlist
   Add products to wishlist
   Remove products from wishlist
   View wishlist

📦 Order Management
   Checkout
   Place orders
   Cash on Delivery
   Online payment integration placeholder
   Order history
   Order details
   Order status tracking
   Stock validation during order placement

🔐 Security
   Spring Security
   JWT authentication
   Protected REST APIs
   Role-based access control

⚠️ Exception Handling
   Custom exceptions
   Global exception handling
   Proper HTTP status codes
   Validation error handling
   Standard API responses


 🏗️ Architecture


    ┌─────────────────────┐
    │   Angular Frontend      │
    │ clothing-store-ui       │
    └──────────┬──────────┘
                 │
                 │ REST APIs
                 ▼
    ┌─────────────────────┐
    │    Spring Boot      │
    │      Backend        │
    └──────────┬──────────┘
                 │
    ┌──────────▼──────────┐
    │   Spring Data JPA   │
    │      Hibernate      │
    └──────────┬──────────┘
                 │
                 ▼
    ┌─────────────────────┐
    │       MySQL         │
    └─────────────────────┘
