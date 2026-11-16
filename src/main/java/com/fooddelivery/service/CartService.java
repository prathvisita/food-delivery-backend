package com.fooddelivery.service;

import com.fooddelivery.exception.ResourceNotFoundException;
import com.fooddelivery.model.*;
import com.fooddelivery.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor
public class CartService {
    private final CartRepository carts;
    private final MenuItemRepository menuItems;

    public List<CartItem> getCart(Long customerId) { return carts.findByCustomerId(customerId); }

    @Transactional
    public CartItem add(Long customerId, Long menuItemId, int quantity) {
        if (quantity < 1) throw new IllegalArgumentException("Quantity must be at least 1");
        MenuItem item = menuItems.findById(menuItemId)
            .orElseThrow(() -> new ResourceNotFoundException("Menu item not found: " + menuItemId));
        if (!item.isAvailable()) throw new IllegalArgumentException("Menu item is unavailable");
        return carts.findByCustomerIdAndMenuItemId(customerId, menuItemId).map(existing -> {
            existing.setQuantity(existing.getQuantity() + quantity);
            return carts.save(existing);
        }).orElseGet(() -> {
            CartItem cart = new CartItem();
            cart.setCustomerId(customerId); cart.setMenuItem(item); cart.setQuantity(quantity);
            return carts.save(cart);
        });
    }

    public void remove(Long customerId, Long cartItemId) {
        CartItem item = carts.findById(cartItemId)
            .orElseThrow(() -> new ResourceNotFoundException("Cart item not found: " + cartItemId));
        if (!item.getCustomerId().equals(customerId)) throw new IllegalArgumentException("Cart item does not belong to customer");
        carts.delete(item);
    }
}
