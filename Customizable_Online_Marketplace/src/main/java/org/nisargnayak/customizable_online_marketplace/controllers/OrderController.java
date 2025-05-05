// src/main/java/org/nisargnayak/customizable_online_marketplace/controllers/OrderController.java
package org.nisargnayak.customizable_online_marketplace.controllers;

import jakarta.servlet.http.HttpSession;
import org.nisargnayak.customizable_online_marketplace.models.CartItem;
import org.nisargnayak.customizable_online_marketplace.models.Order;
import org.nisargnayak.customizable_online_marketplace.models.User;
import org.nisargnayak.customizable_online_marketplace.services.CartService;
import org.nisargnayak.customizable_online_marketplace.services.EmailService;
import org.nisargnayak.customizable_online_marketplace.services.InvoiceService;
import org.nisargnayak.customizable_online_marketplace.services.OrderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final EmailService emailService;
    private final InvoiceService invoiceService;
    private final CartService cartService;

    public OrderController(OrderService orderService,
                           EmailService emailService,
                           InvoiceService invoiceService,
                           CartService cartService) {
        this.orderService   = orderService;
        this.emailService   = emailService;
        this.invoiceService = invoiceService;
        this.cartService    = cartService;
    }

    @GetMapping("/checkout")
    public String checkoutPage(HttpSession session, Model model) {
        User u = (User)session.getAttribute("currentUser");
        if (u==null) return "redirect:/login";

        List<CartItem> items = cartService.getCartItems(u.getId());
        double total = items.stream()
                .mapToDouble(i->i.getProduct().getPrice()*i.getQuantity())
                .sum();

        model.addAttribute("cartItems", items);
        model.addAttribute("total", total);
        return "checkout";
    }

    @PostMapping("/checkout")
    public String processCheckout(HttpSession session, Model model) {
        User u = (User)session.getAttribute("currentUser");
        if (u==null) return "redirect:/login";

        Order order = orderService.createOrder(u.getId());
        emailService.sendOrderConfirmationEmail(order, u.getEmail());
        model.addAttribute("order", order);
        return "orderConfirmation";
    }

    @GetMapping("/invoice/{orderId}")
    public ResponseEntity<byte[]> downloadInvoice(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        byte[] pdf = invoiceService.generateInvoicePdf(order);
        HttpHeaders h = new HttpHeaders();
        h.setContentType(MediaType.APPLICATION_PDF);
        h.setContentDispositionFormData("attachment", "invoice_"+orderId+".pdf");
        return new ResponseEntity<>(pdf, h, HttpStatus.OK);
    }
}
