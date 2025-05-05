package org.nisargnayak.customizable_online_marketplace.repositories;

import org.nisargnayak.customizable_online_marketplace.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findBySellerRatingGreaterThanOrderBySellerRatingDesc(double minSellerRating);
    List<Product> findBySubCategoryIn(List<String> subCategories);
    List<Product> findByRatingGreaterThanOrderByRatingDesc(double rating);
}
