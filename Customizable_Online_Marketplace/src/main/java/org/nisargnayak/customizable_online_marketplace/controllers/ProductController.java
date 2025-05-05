// src/main/java/org/nisargnayak/customizable_online_marketplace/controllers/ProductController.java
package org.nisargnayak.customizable_online_marketplace.controllers;

import org.nisargnayak.customizable_online_marketplace.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repo;
    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String all(Model m) {
        m.addAttribute("products", repo.findAll());
        return "top-sellers";
    }

    @GetMapping("/best-sellers")
    public String bestSellers(Model m) {
        m.addAttribute("products",
                repo.findBySellerRatingGreaterThanOrderBySellerRatingDesc(4.5));
        m.addAttribute("category","Best Sellers");
        return "top-sellers";
    }

    @GetMapping("/womens-fashion")
    public String womens(Model m) {
        List<String> subs = List.of("Dresses","T-Shirts","Jeans","Shirts","Shorts","Sneakers","Watches");
        m.addAttribute("products", repo.findBySubCategoryIn(subs));
        m.addAttribute("category","Women’s Fashion");
        return "womens-fashion";
    }

    @GetMapping("/mens-fashion")
    public String mens(Model m) {
        List<String> subs = List.of("T-Shirts","Jeans","Shirts","Shorts","Sneakers","Shoes","Wallets","Watches");
        m.addAttribute("products", repo.findBySubCategoryIn(subs));
        m.addAttribute("category","Men’s Fashion");
        return "mens-fashion";
    }

    @GetMapping("/top-beauty-products")
    public String beauty(Model m) {
        List<String> subs = List.of("Skincare","Makeup","Hair Care");
        m.addAttribute("products", repo.findBySubCategoryIn(subs));
        m.addAttribute("category","Top Beauty Products");
        return "top-beauty-products";
    }

    @GetMapping("/home-essentials")
    public String essentials(Model m) {
        List<String> subs = List.of("Kitchen","Lighting","Chairs","Sofa","Dining","Beds","Wardrobes","Tables","Rugs","Clocks","Curtains");
        m.addAttribute("products", repo.findBySubCategoryIn(subs));
        m.addAttribute("category","Home Essentials");
        return "home-essentials";
    }

    @GetMapping("/top-rated")
    public String topRated(Model m) {
        m.addAttribute("products",
                repo.findByRatingGreaterThanOrderByRatingDesc(4.5));
        m.addAttribute("category","Top Rated Products");
        return "top-rated";
    }
}
