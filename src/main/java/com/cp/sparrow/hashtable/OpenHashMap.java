package com.cp.sparrow.hashtable;

import com.cp.sparrow.hashtable.hash.ModHashResolver;
import com.cp.sparrow.hashtable.hash.SimpleModHashResolver;
import com.cp.sparrow.hashtable.prime.LargePrimes;
import com.cp.sparrow.hashtable.prime.Primes;
import com.cp.sparrow.hashtable.probe.AbstractProber;
import com.cp.sparrow.hashtable.probe.LinearProber;

import java.util.Iterator;

/**
 * Implementation open addressing strategy
 * @author anatoly vorobyov
 */
public class OpenHashMap implements OpenMap {
    private Primes primes = new LargePrimes();
    private ModHashResolver hashResolver;
    private AbstractProber prober;
    private PrimitiveEntry[] entries;
    private double loadFactor = 0.75d;
    private int sizeArray = primes.getStartPrime();
    private int numberElements;
    private int loadFactorSize;

    public OpenHashMap() {
        entries = new PrimitiveEntry[sizeArray];
        hashResolver = new SimpleModHashResolver(sizeArray);
        prober = new LinearProber(sizeArray);
    }

    public OpenHashMap(int size) {
        sizeArray = size;
        entries = new PrimitiveEntry[sizeArray];
        hashResolver = new SimpleModHashResolver(sizeArray);
        prober = new LinearProber(sizeArray);
    }

    public OpenHashMap(int size, double loadFactor) {
        this(size);
        if (loadFactor < 1) {
            this.loadFactor = loadFactor;
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return numberElements;
    }

    @Override
    public void clear() {
        numberElements = 0;
        entries = new PrimitiveEntry[sizeArray];
    }

    @Override
    public long put(int key, long value) {
        if (ifNeedResize()) resize(getDoubleSize());

        int hashIndex = findHashIndex(key);
        PrimitiveEntry previousEntry = entries[hashIndex];

        if (previousEntry != null) {
            previousEntry.setValue(value);
        } else {
            entries[hashIndex] = new PrimitiveEntry(key, value);
            numberElements++;
        }
        return value;
    }

    @Override
    public long get(int key) throws NotFoundKeyException {
        int hashIndex = findHashIndex(key);

        PrimitiveEntry entry = entries[hashIndex];
        if (entry != null) {
            return entry.getValue();
        }
        throw new NotFoundKeyException();
    }

    @Override
    public Iterator<PrimitiveEntry> getIterator() {
        return new PrimitiveEntryIterator();
    }

    /**
     * Used in case need to ensure capacity
     * @param entry will recalculate hash and put into new array
     */
    private void put(PrimitiveEntry entry) {
        int hashIndex = findHashIndex(entry.getKey());
        entries[hashIndex] = entry;
    }

    /**
     * Find free index to put new {@link PrimitiveEntry}
     * @param key is used to find in case collision
     * @return index of free space in array
     */
    private int findHashIndex(int key) {
        int hashIndex = hashResolver.getHashCode(key);
        while (collisionExists(hashIndex, key)) {
            hashIndex = prober.getNextIndex(hashIndex);
        }
        return hashIndex;
    }

    /**
     * Multiply size of current array twice
     * @return odd number
     */
    private int getDoubleSize() {
        int oddNewSize = (sizeArray * 2) + 1;
        return primes.getNextPrime(oddNewSize);
    }

    private boolean ifNeedResize() {
        return numberElements >= loadFactorSize;
    }

    /**
     * Check if there is collision in sell with {@param index}
     * @param index will be checked is free or not
     * @param key is used to check collision
     */
    private boolean collisionExists(int index, int key) {
        PrimitiveEntry entry = entries[index];
        return entry != null && key != entry.getKey();
    }

    private void resize(int newSize) {
        loadFactor = sizeArray * loadFactor;
        PrimitiveEntry[] oldEntries = entries;
        entries = new PrimitiveEntry[newSize];
        sizeArray = newSize;
        prober.setSizeArray(newSize);
        hashResolver.setSizeArray(newSize);
        for (PrimitiveEntry entry : oldEntries) {
            if (entry != null) {
                put(entry);
            }
        }
    }
    public void setHashResolver(ModHashResolver hashResolver) {
        this.hashResolver = hashResolver;
        hashResolver.setSizeArray(sizeArray);
    }

    public void setPrimes(Primes primes) {
        this.primes = primes;
    }

    public void setProber(AbstractProber prober) {
        this.prober = prober;
        prober.setSizeArray(sizeArray);
    }

    private class PrimitiveEntryIterator implements Iterator<PrimitiveEntry> {
        private int index;

        @Override
        public boolean hasNext() {
            return index < numberElements;
        }

        @Override
        public PrimitiveEntry next() {
            return entries[index++];
        }
    }
}
