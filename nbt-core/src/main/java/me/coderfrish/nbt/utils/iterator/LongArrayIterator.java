package me.coderfrish.nbt.utils.iterator;

import java.util.Iterator;

public class LongArrayIterator implements Iterator<Long> {
    private final long[] array;
    private int offest = 0;

    public LongArrayIterator(long[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return offest < array.length;
    }

    @Override
    public Long next() {
        return array[offest++];
    }
}
