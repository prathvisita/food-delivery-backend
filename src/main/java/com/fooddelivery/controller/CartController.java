package com.fooddelivery.controller;

import com.fooddelivery.model.CartItem;
import com.fooddelivery.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController @RequestMapping("/api/customers/{customerId}/cart") @RequiredArgsConstructor
public class CartController {
    private final CartService service;

    @GetMapping public List<CartItem> get(@PathVariable Long customerId) { return service.getCart(customerId); }
    @PostMapping("/items") public CartItem add(@PathVariable Long customerId, @RequestBody Map<String, Integer> body) {
        return service.add(customerId, body.get("menuItemId").longValue(), body.getOrDefault("quantity", 1));
    }
    @DeleteMapping("/items/{cartItemId}") public void remove(@PathVariable Long customerId, @PathVariable Long cartItemId) {
        service.remove(customerId, cartItemId);
    }
}
