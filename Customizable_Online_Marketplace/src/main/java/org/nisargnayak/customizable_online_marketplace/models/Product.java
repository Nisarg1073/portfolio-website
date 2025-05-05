package org.nisargnayak.customizable_online_marketplace.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_image_url")
    private String productImageUrl;

    private double price;
    private String description;
    private double rating;

    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "seller_rating")
    private double sellerRating;

    @Column(name = "seller_image_url")
    private String sellerImageUrl;

    private int stock;
    private String category;

    @Column(name = "sub_category")
    private String subCategory;
}
