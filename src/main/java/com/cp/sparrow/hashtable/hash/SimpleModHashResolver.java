package com.cp.sparrow.hashtable.hash;

/**
 * Used to calculate the hash code
 * which is used as index
 * @author anatoly vorobyov
 * @see com.cp.sparrow.hashtable.OpenHashMap
 * @see com.cp.sparrow.hashtable.OpenMap
 */
public class SimpleModHashResolver extends ModHashResolver {

    public SimpleModHashResolver(int sizeArray) {
        super(sizeArray);
    }

    /**
     * Number converts to appropriate index for the array
     * use method mask to make positive value
     * @param number key for the {@link com.cp.sparrow.hashtable.OpenHashMap}
     * @return hash code of number
     */
    @Override
    public int getHashCode(int number) {
        return mask(number) % sizeArray ;
    }
}
