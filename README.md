# Food Delivery Backend

A starter REST API for a food ordering and delivery platform, built with Java 21, Spring Boot 3, Spring Data JPA and MySQL.

## Features in this starter
- Create and browse restaurants
- Add menu items to restaurants and view menus
- Add/remove cart items
- Place orders from a customer's cart
- View order history and order details
- Update order lifecycle status
- Request validation and centralized API error responses

## Prerequisites
- JDK 21
- Maven 3.9+
- MySQL 8+

## Database setup
Create a MySQL user/password or use the defaults in `application.properties`:
- database: `food_delivery_db`
- username: `root`
- password: `root`

Override credentials with environment variables:
- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

## Run
```bash
mvn spring-boot:run
```
The API starts at `http://localhost:8080`.

## API quick start

### 1. Create a restaurant
`POST /api/restaurants`
```json
{
  "name": "Spice Garden",
  "address": "Hubballi, Karnataka",
  "cuisine": "North Indian",
  "active": true
}
```

### 2. Add a menu item
`POST /api/restaurants/1/menu`
```json
{
  "name": "Paneer Butter Masala",
  "description": "Paneer in a creamy tomato gravy",
  "price": 220.00,
  "available": true
}
```

### 3. Add to cart
`POST /api/customers/101/cart/items`
```json
{
  "menuItemId": 1,
  "quantity": 2
}
```

### 4. Place order
`POST /api/customers/101/orders`

### 5. View order history
`GET /api/customers/101/orders`

### 6. Update order status
`PATCH /api/orders/1/status`
```json
{ "status": "PREPARING" }
```

## Main API endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/restaurants` | List restaurants |
| POST | `/api/restaurants` | Create restaurant |
| GET | `/api/restaurants/{id}/menu` | View menu |
| POST | `/api/restaurants/{id}/menu` | Add menu item |
| GET | `/api/customers/{customerId}/cart` | View cart |
| POST | `/api/customers/{customerId}/cart/items` | Add item |
| DELETE | `/api/customers/{customerId}/cart/items/{cartItemId}` | Remove item |
| POST | `/api/customers/{customerId}/orders` | Place order |
| GET | `/api/customers/{customerId}/orders` | Order history |
| GET | `/api/orders/{orderId}` | Order details |
| PATCH | `/api/orders/{orderId}/status` | Update order status |

## Important next steps before production
This is a learning/MVP foundation, not a production-ready service. Customer IDs are currently passed in the URL for easy local testing. Add registration/login, password hashing and JWT authorization before exposing it publicly. Also add payment gateway integration, delivery partner assignment, ownership checks, stock handling, restaurant/cart consistency rules, pagination, audit logging, integration tests and Docker deployment.
