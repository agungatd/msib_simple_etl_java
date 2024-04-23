package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataExtractor {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "password";

    public static List<Order> extractOrders() {
        List<Order> orders = new ArrayList<>();

        try (
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders")
        ){
            while (rs.next()) {
                int orderId = rs.getInt("id");
                String customerName = rs.getString("customer_name");
                Date orderDate = rs.getDate("order_date");
                double totalAmount = rs.getDouble("total_amount");
                String status = rs.getString("status");

                orders.add(new Order(orderId,customerName,orderDate,totalAmount,status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }
}
