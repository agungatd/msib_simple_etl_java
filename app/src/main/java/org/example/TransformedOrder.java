package org.example;
import java.time.LocalDate;

public class TransformedOrder {
    private int id;
    private String customerName;
    private LocalDate orderDate;
    /**
     * new fields in transformedOrder
     * - vat: the rate of value added tax
     * - net amount: VAT * total amount
     */
    private double vat;
    private double totalAmount;
    private double netAmount;
    private String status; // e.g., "placed", "shipped", "delivered", "cancelled"
    
    public TransformedOrder(
        int orderId, 
        String customerName, 
        LocalDate orderDate, 
        double vat, 
        double totalAmount, 
        double netAmount, 
        String status
    ) {
        this.id = orderId;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.vat = vat;
        this.totalAmount = totalAmount;
        this.netAmount = netAmount;
        this.status = status;
    }
    
    // Getters and setters for all fields
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public LocalDate getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public double getVat() {
        return vat;
    }
    
    public void setVat(double vat) {
        this.vat = vat;
    }

    public double getNetAmount() {
        return netAmount;
    }
    
    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }
}
