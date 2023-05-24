package be.eafcuccle.projint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PrimeNumberGeneratorTest {
    @Test
    void negativeMaxNumber() {
        assertThrows(IllegalArgumentException.class, () -> new PrimeNumberGenerator(-1));
    }

    @Test
    void lessThanTwoMaxNumber() {
        PrimeNumberGenerator generator = new PrimeNumberGenerator(1);
        assertArrayEquals(new int[0], generator.generatePrimeNumbers());
    }

    @Test
    void generateToTwo() {
        PrimeNumberGenerator generator = new PrimeNumberGenerator(2);
        assertArrayEquals(new int[]{2}, generator.generatePrimeNumbers());
    }

    @Test
    void generateToThree() {
        PrimeNumberGenerator generator = new PrimeNumberGenerator(3);
        assertArrayEquals(new int[]{2, 3}, generator.generatePrimeNumbers());
    }

    @Test
    void generateToSeven() {
        PrimeNumberGenerator generator = new PrimeNumberGenerator(7);
        assertArrayEquals(new int[]{2, 3, 5, 7}, generator.generatePrimeNumbers());
    }

    @Test
    void generateToTen() {
        PrimeNumberGenerator generator = new PrimeNumberGenerator(10);
        assertArrayEquals(new int[]{2, 3, 5, 7}, generator.generatePrimeNumbers());
    }

    @Test
    void generateToOneHundred() {
        PrimeNumberGenerator generator = new PrimeNumberGenerator(100);
        assertArrayEquals(new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97}, generator.generatePrimeNumbers());
    }
}