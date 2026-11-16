package com.fooddelivery.controller;

import com.fooddelivery.model.*;
import com.fooddelivery.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/restaurants") @RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService service;

    @GetMapping public List<Restaurant> all() { return service.all(); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Restaurant create(@Valid @RequestBody Restaurant restaurant) { return service.create(restaurant); }
    @GetMapping("/{id}") public Restaurant get(@PathVariable Long id) { return service.get(id); }
    @GetMapping("/{id}/menu") public List<MenuItem> menu(@PathVariable Long id) { return service.menu(id); }
    @PostMapping("/{id}/menu") @ResponseStatus(HttpStatus.CREATED)
    public MenuItem addMenuItem(@PathVariable Long id, @Valid @RequestBody MenuItem item) { return service.addMenuItem(id, item); }
}
