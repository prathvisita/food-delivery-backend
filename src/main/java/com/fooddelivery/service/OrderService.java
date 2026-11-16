package com.fooddelivery.service;

import com.fooddelivery.exception.ResourceNotFoundException;
import com.fooddelivery.model.*;
import com.fooddelivery.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service @RequiredArgsConstructor
public class OrderService {
    private final CartRepository carts;
    private final OrderRepository orders;

    @Transactional
    public CustomerOrder placeOrder(Long customerId) {
        List<CartItem> cart = carts.findByCustomerId(customerId);
        if (cart.isEmpty()) throw new IllegalArgumentException("Cart is empty");

        CustomerOrder order = new CustomerOrder();
        order.setCustomerId(customerId);
        double total = 0;
        for (CartItem cartItem : cart) {
            MenuItem menu = cartItem.getMenuItem();
            if (!menu.isAvailable()) throw new IllegalArgumentException(menu.getName() + " is unavailable");
            OrderItem line = new OrderItem();
            line.setOrder(order);
            line.setItemName(menu.getName());
            line.setUnitPrice(menu.getPrice());
            line.setQuantity(cartItem.getQuantity());
            order.getItems().add(line);
            total += menu.getPrice() * cartItem.getQuantity();
        }
        order.setTotalAmount(Math.round(total * 100.0) / 100.0);
        CustomerOrder saved = orders.save(order);
        carts.deleteByCustomerId(customerId);
        return saved;
    }

    public List<CustomerOrder> history(Long customerId) {
        return orders.findByCustomerIdOrderByCreatedAtDesc(customerId);
    }

    public CustomerOrder get(Long orderId) {
        return orders.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderId));
    }

    public CustomerOrder updateStatus(Long orderId, OrderStatus status) {
        CustomerOrder order = get(orderId);
        if (order.getStatus() == OrderStatus.DELIVERED || order.getStatus() == OrderStatus.CANCELLED)
            throw new IllegalArgumentException("A completed order cannot be changed");
        order.setStatus(status);
        return orders.save(order);
    }
}
