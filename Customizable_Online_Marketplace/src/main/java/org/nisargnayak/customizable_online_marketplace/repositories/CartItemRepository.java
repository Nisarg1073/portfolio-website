package org.nisargnayak.customizable_online_marketplace.repositories;

import org.nisargnayak.customizable_online_marketplace.models.CartItem;
import org.nisargnayak.customizable_online_marketplace.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Find all cart items for a given user
    List<CartItem> findByUser(User user);
}
