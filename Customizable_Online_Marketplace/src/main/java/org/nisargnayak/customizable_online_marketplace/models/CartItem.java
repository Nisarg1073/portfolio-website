package org.nisargnayak.customizable_online_marketplace.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to the user who owns this cart item
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Link to the product
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Quantity of that product in the cart
    private int quantity;
}
