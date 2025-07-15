package me.coderfrish.nbt.utils.iterator;

import java.util.Iterator;

public class ByteArrayIterator implements Iterator<Byte> {
    private final byte[] array;
    private int offest = 0;

    public ByteArrayIterator(byte[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return offest < array.length;
    }

    @Override
    public Byte next() {
        return array[offest++];
    }
}
