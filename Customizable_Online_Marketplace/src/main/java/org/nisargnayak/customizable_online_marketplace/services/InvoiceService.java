package org.nisargnayak.customizable_online_marketplace.services;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.nisargnayak.customizable_online_marketplace.models.Order;
import org.nisargnayak.customizable_online_marketplace.models.OrderItem;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;

@Service
public class InvoiceService {

    public byte[] generateInvoicePdf(Order order) {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            // Title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Paragraph title = new Paragraph("Invoice", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));

            // Order details
            Font detailsFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            document.add(new Paragraph("Order ID: " + order.getId(), detailsFont));
            document.add(new Paragraph("Order Date: " + order.getOrderDate(), detailsFont));
            document.add(new Paragraph("Customer: " + order.getUser().getFirstName() + " " + order.getUser().getLastName(), detailsFont));
            document.add(new Paragraph(" "));

            // Order items table
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.addCell("Product");
            table.addCell("Price");
            table.addCell("Quantity");
            table.addCell("Subtotal");

            for (OrderItem item : order.getOrderItems()) {
                table.addCell(item.getProductName());
                table.addCell(String.valueOf(item.getPrice()));
                table.addCell(String.valueOf(item.getQuantity()));
                table.addCell(String.valueOf(item.getPrice() * item.getQuantity()));
            }

            document.add(table);
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Total: $" + order.getTotal(), detailsFont));

            document.close();
        } catch(DocumentException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
}
