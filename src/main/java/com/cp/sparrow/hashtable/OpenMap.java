package com.cp.sparrow.hashtable;

import java.util.Iterator;

/**
 * @author anatoly vorobyov
 */
public interface OpenMap {
    long put(int key, long value);

    long get(int key) throws NotFoundKeyException;

    boolean isEmpty();

    int size();

    void clear();

    default Iterator<PrimitiveEntry> getIterator() {
        throw new UnsupportedOperationException();
    }

    default long remove(int key) {
        throw new UnsupportedOperationException();
    }

    default boolean containsValue(long value) {
        throw new UnsupportedOperationException();
    }

    default boolean containsKey(int key) {
        throw new UnsupportedOperationException();
    }

}
