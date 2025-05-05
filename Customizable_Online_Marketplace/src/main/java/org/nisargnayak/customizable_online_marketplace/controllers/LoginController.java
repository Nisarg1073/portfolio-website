// src/main/java/org/nisargnayak/customizable_online_marketplace/controllers/LoginController.java
package org.nisargnayak.customizable_online_marketplace.controllers;

import jakarta.servlet.http.HttpSession;
import org.nisargnayak.customizable_online_marketplace.models.User;
import org.nisargnayak.customizable_online_marketplace.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserService userService;
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String usernameOrEmail,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        try {
            User user = userService.login(usernameOrEmail, password);
            session.setAttribute("currentUser", user);
            return "redirect:/homepage";
        } catch(Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }
}
