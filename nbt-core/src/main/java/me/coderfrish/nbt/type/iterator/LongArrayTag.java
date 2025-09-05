package me.coderfrish.nbt.type.iterator;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.ElementTag;
import me.coderfrish.nbt.utils.iterator.LongArrayIterator;

import java.util.Iterator;

public class LongArrayTag extends ElementTag implements Iterable<Long> {
    private final long[] array;

    public LongArrayTag(long[] array) {
        this.array = array;
    }

    public LongArrayTag(int size) {
        this(new long[size]);
    }

    public long get(int i) {
        return array[i];
    }

    public LongArrayTag set(int i, long v) {
        array[i] = v;
        return this;
    }

    public int size() {
        return array.length;
    }

    @Override
    public LongArrayTag getAsLongArray() {
        return this;
    }

    @Override
    public TagType type() {
        return TagType.LONG_ARRAY;
    }

    @Override
    public Iterator<Long> iterator() {
        return new LongArrayIterator(array);
    }
}
