package com.cp.sparrow.hashtable.prime;

/**
 * Class used to generate prime numbers
 * @see com.cp.sparrow.hashtable.OpenHashMap
 * @author anatoly vorobyov
 */
public class LargePrimes implements Primes {
    /**
     * Start value of prime
     * 0x11 = 17
     */
    private int startPrime = 0x11;

    /**
     * Don`t guarantee the result if receive even value
     */
    @Override
    public int getNextPrime(int value) {
        if (value <= startPrime) return -1;

        int result = value;
        boolean readyResult = true;
        while (readyResult) {
            if (isOddPrime(result)) {
                readyResult = false;
            } else {
                result += 2;
            }
        }
        return result;
    }

    /**
     * Accept only odd numbers,
     * in other case the result is wrong
     */
    private boolean isOddPrime(int oddAndLargeNumber) {
        for (int i = 3; i < (oddAndLargeNumber / 2); i = i + 2) {
            if ((oddAndLargeNumber % i) == 0) return false;
        }
        return true;
    }


    /**
     * @return start large value
     */
    @Override
    public int getStartPrime() {
        return startPrime;
    }
}
