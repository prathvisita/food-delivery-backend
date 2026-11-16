package com.fooddelivery.controller;

import com.fooddelivery.model.*;
import com.fooddelivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController @RequestMapping("/api") @RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @PostMapping("/customers/{customerId}/orders") @ResponseStatus(HttpStatus.CREATED)
    public CustomerOrder place(@PathVariable Long customerId) { return service.placeOrder(customerId); }
    @GetMapping("/customers/{customerId}/orders")
    public List<CustomerOrder> history(@PathVariable Long customerId) { return service.history(customerId); }
    @GetMapping("/orders/{orderId}") public CustomerOrder get(@PathVariable Long orderId) { return service.get(orderId); }
    @PatchMapping("/orders/{orderId}/status")
    public CustomerOrder status(@PathVariable Long orderId, @RequestBody Map<String, String> body) {
        return service.updateStatus(orderId, OrderStatus.valueOf(body.get("status").toUpperCase()));
    }
}
