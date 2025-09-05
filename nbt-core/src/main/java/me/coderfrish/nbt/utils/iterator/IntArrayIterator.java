package me.coderfrish.nbt.utils.iterator;

import java.util.Iterator;

public class IntArrayIterator implements Iterator<Integer> {
    private final int[] array;
    private int offest = 0;

    public IntArrayIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return offest < array.length;
    }

    @Override
    public Integer next() {
        return array[offest++];
    }
}
