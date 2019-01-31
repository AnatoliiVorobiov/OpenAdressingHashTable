package com.cp.sparrow.hashtable.probe;

/**
 * Interface is used to resolver the collision
 * @see com.cp.sparrow.hashtable.OpenHashMap
 * @author anatoly vorobyov
 */
public interface Prober {
    /**
     * Return next index in case of collision
     * @param previousIndex index which is occupied
     * @return next index
     */
    int getNextIndex(int previousIndex);
}
