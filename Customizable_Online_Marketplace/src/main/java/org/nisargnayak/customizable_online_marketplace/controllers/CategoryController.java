// src/main/java/org/nisargnayak/customizable_online_marketplace/controllers/CategoryController.java
package org.nisargnayak.customizable_online_marketplace.controllers;

import org.nisargnayak.customizable_online_marketplace.services.CategoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService srv;
    public CategoryController(CategoryService srv) {
        this.srv = srv;
    }

    @GetMapping
    public List<String> getAll() {
        return srv.getAllCategories();
    }
}
