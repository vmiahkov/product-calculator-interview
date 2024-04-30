package org.example;

import java.util.List;

public class PriceCalculator {

    /**
     * Calculate the total price of all items in all orders of a customer.
     * Customers get a 10% discount for a product if they buy more than 5 of it.
     * Customers also get a discount based on their membership.
     */
    public double calculateTotal(Customer customer) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public record Customer(Membership membership, List<Order> orders) {

    }

    public record Order(List<Item> items) {

    }

    public record Item(Product product, int quantity) {

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
}
