package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataExtractorTest {

    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        // Set up the test database connection
        connection = DriverManager.getConnection(DataExtractor.DB_URL, DataExtractor.DB_USER, DataExtractor.DB_PASSWORD);
        // Create a test table and insert some test data
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS test_orders (id SERIAL PRIMARY KEY, customer_name TEXT, order_date DATE, total_amount DOUBLE PRECISION, status TEXT)");
        statement.executeUpdate("INSERT INTO orders (customer_name, order_date, total_amount, status) VALUES ('John Doe', '2023-04-22', 100.0, 'PENDING')");
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Clean up the test database
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE orders");
        connection.close();
    }

    @Test
    void testExtractOrders() {
        List<Order> orders = DataExtractor.extractOrders();
        assertNotNull(orders);
        assertEquals(1, orders.size());
        Order order = orders.get(0);
        assertEquals("John Doe", order.getCustomerName());
        assertEquals(100.0, order.getTotalAmount(), 0.001);
        assertEquals("PENDING", order.getStatus());
    }
}