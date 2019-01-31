package com.cp.sparrow.hashtable.hash;

/**
 * Main interface which used as hash resolver for keys
 * in {@link com.cp.sparrow.hashtable.OpenHashMap}
 * @author anatoly vorobyov
 */
public interface HashResolver {
    /**
     * Number converts to appropriate index for the array
     * use method mask to make positive value
     * @param number key for the {@link com.cp.sparrow.hashtable.OpenHashMap}
     * @return hash code of number
     */
    int getHashCode(int number);
}
