// src/main/java/org/nisargnayak/customizable_online_marketplace/controllers/PaymentController.java
package org.nisargnayak.customizable_online_marketplace.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {
    @GetMapping("/payment")
    public String showPayment() {
        return "payment";
    }
}
