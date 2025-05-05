package org.nisargnayak.customizable_online_marketplace.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // Map both "/" and "/homepage" to homepage.html
    @GetMapping({"/", "/homepage"})
    public String homepage() {
        return "homepage";
    }

    // Pure static content
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
