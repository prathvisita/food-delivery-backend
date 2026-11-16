package com.fooddelivery.repository;
import com.fooddelivery.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCustomerId(Long customerId);
    Optional<CartItem> findByCustomerIdAndMenuItemId(Long customerId, Long menuItemId);
    void deleteByCustomerId(Long customerId);
}
