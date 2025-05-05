// src/main/java/org/nisargnayak/customizable_online_marketplace/controllers/CartController.java
package org.nisargnayak.customizable_online_marketplace.controllers;

import org.nisargnayak.customizable_online_marketplace.models.CartItem;
import org.nisargnayak.customizable_online_marketplace.services.CartService;
import org.nisargnayak.customizable_online_marketplace.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService,
                          UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping
    public String showCart(Model model) {
        Long userId = getCurrentUserId();
        List<CartItem> cartItems = cartService.getCartItems(userId);
        model.addAttribute("cartItems", cartItems);

        double total = cartItems.stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum();
        model.addAttribute("total", total);

        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam int quantity,
                            RedirectAttributes ra) {
        if (quantity <= 0) {
            ra.addFlashAttribute("error", "Invalid quantity.");
            return "redirect:/homepage";
        }
        cartService.addToCart(getCurrentUserId(), productId, quantity);
        ra.addFlashAttribute("success", "Added to cart!");
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long cartItemId) {
        cartService.removeFromCart(cartItemId);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateQuantity(@RequestParam Long cartItemId,
                                 @RequestParam int quantity) {
        cartService.updateQuantity(cartItemId, quantity);
        return "redirect:/cart";
    }

    private Long getCurrentUserId() {
        return 1L; // TODO: replace with real session lookup
    }
}
