package com.cp.sparrow.hashtable.probe;

/**
 * Simple implementation to resolve situation when
 * there is the collision
 * @see com.cp.sparrow.hashtable.OpenHashMap
 * @author anatoly vorobyov
 */
public class LinearProber extends AbstractProber {
    /**
     * Random prime, number is used to find next index
     */
    private int step = 0x1F7;

    public LinearProber(int sizeArray) {
        super(sizeArray);
    }

    public LinearProber(int sizeArray, int step) {
        super(sizeArray);
        this.step = step;
    }

    @Override
    public int getNextIndex(int previousIndex) {
        int nextIndex = previousIndex + step;
        return nextIndex % sizeArray;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
