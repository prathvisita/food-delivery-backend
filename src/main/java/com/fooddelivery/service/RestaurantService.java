package com.fooddelivery.service;

import com.fooddelivery.exception.ResourceNotFoundException;
import com.fooddelivery.model.*;
import com.fooddelivery.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service @RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurants;
    private final MenuItemRepository menuItems;

    public List<Restaurant> all() { return restaurants.findAll(); }
    public Restaurant create(Restaurant restaurant) { return restaurants.save(restaurant); }
    public Restaurant get(Long id) { return restaurants.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found: " + id)); }
    public MenuItem addMenuItem(Long restaurantId, MenuItem item) {
        item.setRestaurant(get(restaurantId));
        return menuItems.save(item);
    }
    public List<MenuItem> menu(Long restaurantId) {
        get(restaurantId);
        return menuItems.findByRestaurantId(restaurantId);
    }
}
