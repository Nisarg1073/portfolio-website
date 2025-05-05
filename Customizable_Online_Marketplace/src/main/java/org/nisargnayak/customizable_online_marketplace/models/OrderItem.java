package org.nisargnayak.customizable_online_marketplace.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to the associated order
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // Snapshot of product details at order time
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
}
