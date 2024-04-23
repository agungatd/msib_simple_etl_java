package org.example;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;

public class DataLoader {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    public static void loadTransformedOrders(List<TransformedOrder> transformedOrders) {
        String sql = "INSERT INTO transformed_orders (id, customer_name, order_date, vat, total_amount, net_amount, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Properties props = new Properties();

        props.setProperty("user", "postgres");
        props.setProperty("password", "password");
        props.setProperty("currentSchema", "dwh");

        try (
            Connection conn = DriverManager.getConnection(DB_URL, props);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            for (TransformedOrder order: transformedOrders) {
                stmt.setInt(1, order.getId());
                stmt.setString(2, order.getCustomerName());
                stmt.setDate(3, (Date) order.getOrderDate());
                stmt.setDouble(4, order.getVat());
                stmt.setDouble(5, order.getTotalAmount());
                stmt.setDouble(6, order.getNetAmount());
                stmt.setString(7, order.getStatus());

                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
