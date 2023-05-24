package be.eafcuccle.projint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class PrimeNumberGenerator {
    public static final Logger logger = LoggerFactory.getLogger(PrimeNumberGenerator.class);
    private final int maxPrimeNumber;
    private boolean[] booleanArray;

    public PrimeNumberGenerator(int maxPrimeNumber) {
        this.maxPrimeNumber = maxPrimeNumber;
        logger.info("PrimeNumberGenerator created with maxPrimeNumber = {}", maxPrimeNumber);
        if (maxPrimeNumber < 0) {
            logger.error("maxPrimeNumber must be positive");
            throw new IllegalArgumentException("maxPrimeNumber must be positive");
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            logger.error("Missing maxPrimeNumber argument");
            System.out.println("Usage: java be.eafcuccle.projint.PrimeNumberGenerator <maxPrimeNumber>");
            System.exit(1);
        }
        int max = Integer.valueOf(args[0], 10);
        PrimeNumberGenerator primeNumberGenerator = new PrimeNumberGenerator(max);
        int[] primeNumbers = primeNumberGenerator.generatePrimeNumbers();
        for (int primeNumber : primeNumbers) {
            System.out.println(primeNumber);
        }
    }

    int[] generatePrimeNumbers() {
        if (maxPrimeNumber < 2) {
            return new int[0];
        } else {
            generateBooleanArray();
            removeNonPrimeNumbers();
            return getPrimeNumbers();
        }
    }

    private void removeNonPrimeNumbers() {
        int primeNumber = 2;
        while (primeNumber * 2 <= maxPrimeNumber) {
            logger.debug("Removing multiples of {}", primeNumber);
            removeMultiplesOf(primeNumber);
            primeNumber = getNextPrimeNumber(primeNumber);
        }

    }

    private void removeMultiplesOf(int primeNumber) {
        int factor = 2;
        while (primeNumber * factor <= maxPrimeNumber) {
            booleanArray[primeNumber * factor] = false;
            factor++;
        }
    }

    private int getNextPrimeNumber(int currentPrimeNumber) {
        for (int i = currentPrimeNumber + 1; i <= maxPrimeNumber; i++) {
            if (booleanArray[i]) {
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }

    private int[] getPrimeNumbers() {
        int primeNumberCount = countPrimeNumbers();
        int primeNumberIndex = 0;
        int[] primeNumbers = new int[primeNumberCount];
        for (int i = 0; i <= maxPrimeNumber; i++) {
            if (booleanArray[i]) {
                logger.debug("Adding number {} to primeNumbers", i);
                primeNumbers[primeNumberIndex] = i;
                primeNumberIndex++;
            }
        }
        return primeNumbers;
    }

    private int countPrimeNumbers() {
        int count = 0;
        for (int i = 0; i <= maxPrimeNumber; i++) {
            if (booleanArray[i]) {
                count++;
            }
        }
        return count;
    }

    private void generateBooleanArray() {
        int booleanArraySize = maxPrimeNumber + 1;
        booleanArray = new boolean[booleanArraySize];
        Arrays.fill(booleanArray, true);
        booleanArray[0] = false;
        booleanArray[1] = false;
    }
}
