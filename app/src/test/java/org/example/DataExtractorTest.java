package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataExtractorTest {

    private Connection connection;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "password";

    @BeforeEach
    void setUp() throws SQLException {
        // Set up the test database connection
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        // Create a test table and insert some test data
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS test_orders (id serial4 NOT NULL, customer_name varchar NOT NULL, order_date date NOT NULL, total_amount numeric NOT NULL, \"status\" varchar NOT NULL)");
        statement.executeUpdate("INSERT INTO test_orders (id, customer_name, order_date, total_amount, status) VALUES (1, 'John Doe', '2023-04-22', 100.0, 'placed'), (2, 'Jane Doe', '2023-04-12', 300.0, 'delivered')");
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Clean up the test database
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE test_orders");
        connection.close();
    }

    @Test
    void testExtractOrders() {
        List<Order> orders = DataExtractor.extractOrders("test_orders");
        Order order = orders.get(0);
        LocalDate localdate = LocalDate.parse("2023-04-22");

        assertNotNull(orders);
        assertEquals(2, orders.size());
        assertEquals("John Doe", order.getCustomerName());
        assertEquals(localdate, order.getOrderDate());
        assertEquals(100.0, order.getTotalAmount(), 0.001);
        assertEquals("placed", order.getStatus());
    }
}