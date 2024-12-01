package me.coderfrish.nbt;

import java.io.DataOutput;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

@SuppressWarnings("all")
public record LongArrayTag(String key, long[] value) implements ITagBase, Iterable<Long> {
    public LongArrayTag(long[] array) {
        this("", array);
    }

    @Override
    public long[] value() {
        return value;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public void write(DataOutput output) throws Exception {
        if (value == null) {
            output.writeInt(0); // Length of zero
            return;
        }

        output.writeInt(value.length);
        for (Long item : value) {
            output.writeLong(item);
        }
    }

    @Override
    public TagType type() {
        return TagType.LONG_ARRAY;
    }

    public long get(int index) {
        return value[index];
    }

    @Override
    public Iterator<Long> iterator() {
        return Arrays.stream(value).iterator();
    }

    @Override
    public void forEach(Consumer<? super Long> action) {
        for (long item : value) {
            action.accept(item);
        }
    }

    @Override
    public String toString() {
        return "longs" + Arrays.toString(value);
    }
}
