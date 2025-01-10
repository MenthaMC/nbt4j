package me.coderfrish.nbt.type;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.LongStream;

public class NBTLongArray implements Iterable<Long> {
    private final long[] array;

    public NBTLongArray(long[] array) {
        this.array = array;
    }

    public NBTLongArray(int size) {
        this.array = new long[size];
    }

    public long get(int index) {
        return array[index];
    }

    public NBTLongArray set(int index, long value) {
        this.array[index] = value;
        return this;
    }

    public int size() {
        return array.length;
    }

    public LongStream stream() {
        return Arrays.stream(array);
    }

    @Override
    public Iterator<Long> iterator() {
        return stream().iterator();
    }
}
