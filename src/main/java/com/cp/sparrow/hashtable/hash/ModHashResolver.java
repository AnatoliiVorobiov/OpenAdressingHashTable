package com.cp.sparrow.hashtable.hash;

/**
 * @author anatoly vorobyov
 */
public abstract class ModHashResolver implements HashResolver {
    protected int sizeArray;

    public ModHashResolver(int sizeArray) {
        this.sizeArray = sizeArray;
    }

    /**
     * Make positive 31-bite number
     * 0x7FFFFFFF = 0111 1111 11...
     */
    protected int mask(int number) {
        return number & 0x7FFFFFFF;
    }

    public void setSizeArray(int sizeArray) {
        this.sizeArray = sizeArray;
    }
}
