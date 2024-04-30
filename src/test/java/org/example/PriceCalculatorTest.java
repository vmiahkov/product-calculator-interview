package org.example;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.example.PriceCalculator.Membership.*;
import static org.example.PriceCalculator.Product.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PriceCalculatorTest {

    PriceCalculator calculator = new PriceCalculator();

    @ParameterizedTest(name = "Customer {0} should have total of {1}")
    @MethodSource("testData")
    void calculateTotal(PriceCalculator.Customer customer, double expectedTotal) {
        double total = calculator.calculateTotal(customer);
        assertEquals(expectedTotal, total, "Total should be " + expectedTotal + " but was " + total);
    }

    List<Arguments> testData() {
        return List.of(
                Arguments.of(Named.of("Alice", new PriceCalculator.Customer(REGULAR, List.of(
                        new PriceCalculator.Order(List.of(
                                new PriceCalculator.Item(APPLE, 3),
                                new PriceCalculator.Item(BANANA, 5),
                                new PriceCalculator.Item(PEAR, 1)
                        )),
                        new PriceCalculator.Order(List.of(
                                new PriceCalculator.Item(GRAPES, 1),
                                new PriceCalculator.Item(ORANGE, 2)
                        )),
                        new PriceCalculator.Order(List.of(
                                new PriceCalculator.Item(APPLE, 3),
                                new PriceCalculator.Item(PEAR, 2),
                                new PriceCalculator.Item(ORANGE, 3)
                        ))
                ))), 219.5),
                Arguments.of(Named.of("Bob", new PriceCalculator.Customer( PREMIUM, List.of(
                        new PriceCalculator.Order(List.of(
                                new PriceCalculator.Item(APPLE, 3),
                                new PriceCalculator.Item(BANANA, 5)
                        )),
                        new PriceCalculator.Order(List.of(
                                new PriceCalculator.Item(PEAR, 1),
                                new PriceCalculator.Item(GRAPES, 2),
                                new PriceCalculator.Item(ORANGE, 2)
                        )),
                        new PriceCalculator.Order(List.of(
                                new PriceCalculator.Item(PEAR, 2),
                                new PriceCalculator.Item(ORANGE, 3),
                                new PriceCalculator.Item(APPLE, 1)
                        ))
                ))), 214.7),
                Arguments.of(Named.of("Charlie", new PriceCalculator.Customer(VIP, List.of(
                        new PriceCalculator.Order(List.of(
                                new PriceCalculator.Item(APPLE, 4),
                                new PriceCalculator.Item(BANANA, 5),
                                new PriceCalculator.Item(PEAR, 6),
                                new PriceCalculator.Item(GRAPES, 8)
                        )),
                        new PriceCalculator.Order(List.of(
                                new PriceCalculator.Item(ORANGE, 4),
                                new PriceCalculator.Item(APPLE, 2),
                                new PriceCalculator.Item(BANANA, 5),
                                new PriceCalculator.Item(PEAR, 3)
                        )),
                        new PriceCalculator.Order(List.of(
                                new PriceCalculator.Item(GRAPES, 4),
                                new PriceCalculator.Item(ORANGE, 4)
                        ))
                ))), 498.96)
        );
    }
}