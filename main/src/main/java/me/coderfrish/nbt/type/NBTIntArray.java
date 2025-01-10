package me.coderfrish.nbt.type;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;

public class NBTIntArray implements Iterable<Integer> {
    private final int[] array;

    public NBTIntArray(int[] array) {
        this.array = array;
    }

    public NBTIntArray(int size) {
        array = new int[size];
    }

    public int get(int index) {
        return array[index];
    }

    public NBTIntArray set(int index, int value) {
        array[index] = value;
        return this;
    }

    public int size() {
        return array.length;
    }

    public IntStream stream() {
        return Arrays.stream(array);
    }

    @Override
    public Iterator<Integer> iterator() {
        return stream().iterator();
    }
}
