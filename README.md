## E‑Commerce API (Spring Boot)

This is a Spring Boot REST API that covers core e‑commerce features: products, categories, cart, orders, payment methods and users. I kept the design simple, readable, and easy to extend.

### Features
- Product and Category CRUD endpoints
- Cart and Cart Items (add/remove, quantity updates)
- Order flow (convert cart to order)
- Payment Methods definition and linking
- Basic User management
- Global error handling and OpenAPI/Swagger UI docs

### Tech Stack
- Java 17+, Spring Boot (Web, Security, Validation)
- Spring Data JPA (Hibernate)
- Maven
- springdoc‑openapi (Swagger UI)

### Getting Started
1. Make sure Java 17+ and Maven are installed.
2. Start the app:

```bash
mvn spring-boot:run
```

3. Base URL: `http://localhost:8080`
4. Swagger UI: `http://localhost:8080/swagger-ui/index.html`
5. OpenAPI JSON: `http://localhost:8080/v3/api-docs`

### Configuration
Main settings are in `src/main/resources/application.properties` (database, port, etc.). Test profile settings are under `src/test/resources/application-test.properties`.

### Example Requests
```bash
# List products
curl -X GET http://localhost:8080/api/products

# Create a product
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Headphones","price":1299.90,"stock":50,"categoryId":1}'

# Add product to cart
curl -X POST http://localhost:8080/api/cart-items \
  -H "Content-Type: application/json" \
  -d '{"cartId":1, "productId":2, "quantity":1}'

# Create an order
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{"cartId":1, "paymentMethodId":1}'
```

### Project Structure (short)
- `controller`: REST endpoints
- `service` and `service/ımpl`: business logic
- `repository`: JPA repositories
- `model`: JPA entities
- `dto`: request/response models
- `security`: security configuration
- `config`: OpenAPI and other configs

### Testing
```bash
mvn test
```

### Notes
- You can explore and try endpoints quickly via Swagger UI.
- Room to grow: role‑based authorization, JWT, integration tests, etc.

