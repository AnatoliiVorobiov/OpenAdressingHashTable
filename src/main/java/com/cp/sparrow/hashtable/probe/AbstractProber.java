package com.cp.sparrow.hashtable.probe;

/**
 * Class is used to resolver for the collision
 * @see com.cp.sparrow.hashtable.OpenHashMap
 * @author anatoly vorobyov
 */
public abstract class AbstractProber implements Prober {
    protected int sizeArray;

    public AbstractProber() {}

    public AbstractProber(int sizeArray) {
        this.sizeArray = sizeArray;
    }

    public void setSizeArray(int sizeArray) {
        this.sizeArray = sizeArray;
    }
}
