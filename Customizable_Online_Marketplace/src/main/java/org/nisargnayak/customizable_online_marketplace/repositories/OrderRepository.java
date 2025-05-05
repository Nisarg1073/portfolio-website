package org.nisargnayak.customizable_online_marketplace.repositories;

import org.nisargnayak.customizable_online_marketplace.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
