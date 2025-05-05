package org.nisargnayak.customizable_online_marketplace.repositories;

import org.nisargnayak.customizable_online_marketplace.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
