package org.nisargnayak.customizable_online_marketplace.services;

import org.nisargnayak.customizable_online_marketplace.models.Category;
import org.nisargnayak.customizable_online_marketplace.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<String> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(Category::getName) // ✅ This will now work
                .collect(Collectors.toList());
    }
}
