package org.example;

import java.util.List;

public class PriceCalculator {

    /**
     * Calculate the total price of all items in all orders of a customer.
     * Customers get a 10% discount for a product if they buy more than 5 of it.
     * Customers also get a discount based on their membership.
     */
    public double calculateTotal(Customer customer) {
        var total = customer.orders.stream()
                .mapToDouble(order -> calculateTotalInOrder(customer.orders, order))
                .sum();
        return total - total * getMembershipDiscount(customer.membership);
    }

    private double calculateTotalInOrder(List<Order> orders, Order order) {
        return order.items.stream()
                .mapToDouble(item -> calculateItemPrice(orders, item))
                .sum();
    }

    private double calculateItemPrice(List<Order> orders, Item item) {
        var itemPrice = item.product.getPrice() * item.quantity;
        var applyDiscount = applyProductDiscount(orders, item.product);
        return applyDiscount ? itemPrice * 0.9 : itemPrice;
    }

    private boolean applyProductDiscount(List<Order> orders, Product product) {
        var totalProductNumber = orders.stream()
                .flatMap(order -> order.items.stream())
                .filter(item -> item.product.equals(product))
                .mapToInt(item -> item.quantity)
                .sum();
        return totalProductNumber > 5;
    }

    private double getMembershipDiscount(Membership membership) {
        return switch (membership) {
            case VIP -> 0.1;
            case PREMIUM -> 0.05;
            default -> 0.0;
        };
    }

    public enum Membership {
        /**
         * No discount
         */
        REGULAR,
        /**
         * 5% discount
         */
        PREMIUM,
        /**
         * 10% discount
         */
        VIP
    }

    public enum Product {

        APPLE(10.0),
        BANANA(7.5),
        ORANGE(12.5),
        GRAPES(20.5),
        PEAR(15.0);

        private final double price;

        Product(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }
    }

    public record Customer(Membership membership, List<Order> orders) {

    }

    public record Order(List<Item> items) {

    }

    public record Item(Product product, int quantity) {

    }
}
