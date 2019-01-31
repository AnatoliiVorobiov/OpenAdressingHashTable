package com.cp.sparrow.hashtable;

import java.util.Objects;

public class PrimitiveEntry {
    private int key;
    private long value;

    public PrimitiveEntry(int key, long value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "KEY - " + key + " | VALUE - " + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PrimitiveEntry that = (PrimitiveEntry) o;
        return key == that.key &&
                value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
