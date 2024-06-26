/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Extract Data
        List<Order> orders = DataExtractor.extractOrders("orders");

        // Transformed Data
        List<TransformedOrder> transformedOrders = DataTransformer.transformOrders(orders);

        // Load Data
        DataLoader.loadTransformedOrders(transformedOrders);
    }
}
