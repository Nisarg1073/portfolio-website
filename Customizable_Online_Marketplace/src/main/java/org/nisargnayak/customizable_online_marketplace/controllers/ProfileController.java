// src/main/java/org/nisargnayak/customizable_online_marketplace/controllers/ProfileController.java
package org.nisargnayak.customizable_online_marketplace.controllers;

import org.nisargnayak.customizable_online_marketplace.models.Order;
import org.nisargnayak.customizable_online_marketplace.models.User;
import org.nisargnayak.customizable_online_marketplace.repositories.OrderRepository;
import org.nisargnayak.customizable_online_marketplace.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProfileController {

    private final UserService userService;
    private final OrderRepository orderRepo;

    public ProfileController(UserService us, OrderRepository or) {
        this.userService = us;
        this.orderRepo   = or;
    }

    @GetMapping("/profile")
    public String profile(Model m) {
        User u = userService.getUserById(1L);
        m.addAttribute("user", u);
        return "profile";
    }

    @GetMapping("/order-history")
    public String history(Model m) {
        List<Order> orders = orderRepo.findByUserId(1L);
        m.addAttribute("orders", orders);
        return "orderHistory";
    }
}
