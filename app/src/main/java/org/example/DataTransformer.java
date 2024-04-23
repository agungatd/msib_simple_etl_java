package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class DataTransformer {
    public static List<TransformedOrder> transformOrders(List<Order> orders) {
        List<TransformedOrder> transformedOrders = new ArrayList<>();

        for (Order order : orders) {
            // customer name should be uppercase
            String customerName = order.getCustomerName().toUpperCase();

            // add VAT and Net Amount
            double vat = 1.11; // Add 11% tax
            double totalAmount = order.getTotalAmount();
            double netAmount = totalAmount * vat;

            // unchanged fields
            Date orderDate = order.getOrderDate();
            String status = order.getStatus();
            transformedOrders.add(new TransformedOrder(order.getId(), customerName, orderDate, vat, totalAmount, netAmount, status));
        }

        return transformedOrders;
    }
}
