package org.nisargnayak.customizable_online_marketplace.repositories;

import org.nisargnayak.customizable_online_marketplace.models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
}
