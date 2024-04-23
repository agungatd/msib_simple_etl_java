package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class DataExtractor {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "password";

    public static List<Order> extractOrders(String tableName) {
        List<Order> orders = new ArrayList<>();

        try (
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName)
        ){
            while (rs.next()) {
                int orderId = rs.getInt("id");
                String customerName = rs.getString("customer_name");
                LocalDate orderDate = rs.getObject("order_date", LocalDate.class);
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
