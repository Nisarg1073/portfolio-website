package org.nisargnayak.customizable_online_marketplace.services;

import org.nisargnayak.customizable_online_marketplace.models.Order;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOrderConfirmationEmail(Order order, String recipientEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Order Confirmation - Order #" + order.getId());
        message.setText(buildEmailBody(order));
        mailSender.send(message);
    }

    private String buildEmailBody(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("Dear ").append(order.getUser().getFirstName()).append(",\n\n");
        sb.append("Thank you for your order! Your order has been placed successfully.\n");
        sb.append("Order ID: ").append(order.getId()).append("\n");
        sb.append("Order Date: ").append(order.getOrderDate()).append("\n");
        sb.append("Total: $").append(order.getTotal()).append("\n\n");
        sb.append("We will notify you once your order is shipped.\n");
        sb.append("Thank you for shopping with Custom Marketplace!\n\n");
        sb.append("Best regards,\n");
        sb.append("Custom Marketplace Team");
        return sb.toString();
    }
}
