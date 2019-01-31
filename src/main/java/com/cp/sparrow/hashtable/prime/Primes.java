package com.cp.sparrow.hashtable.prime;

public interface Primes {
    /**
     * Method look for nearest prime number close to value
     * @param value start point for looking
     * @return prime
     */
    int getNextPrime(int value);

    /**
     * @return start prime
     */
    int getStartPrime();
}
