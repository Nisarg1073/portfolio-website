package org.nisargnayak.customizable_online_marketplace.models;

import jakarta.persistence.*;

@Entity
@Table(name = "sub_categories")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;
    private String description;

    // Getters & Setters
}
