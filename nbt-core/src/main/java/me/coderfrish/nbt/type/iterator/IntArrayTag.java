package me.coderfrish.nbt.type.iterator;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.ElementTag;
import me.coderfrish.nbt.utils.iterator.IntArrayIterator;

import java.util.Iterator;

public class IntArrayTag extends ElementTag implements Iterable<Integer> {
    private final int[] array;

    public IntArrayTag(int[] array) {
        this.array = array;
    }

    public IntArrayTag(int size) {
        this(new int[size]);
    }

    public int get(int i) {
        return array[i];
    }

    public IntArrayTag set(int i, int v) {
        array[i] = v;
        return this;
    }

    public int size() {
        return array.length;
    }

    @Override
    public TagType type() {
        return TagType.INT_ARRAY;
    }

    @Override
    public IntArrayTag getAsIntArray() {
        return this;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IntArrayIterator(array);
    }
}
