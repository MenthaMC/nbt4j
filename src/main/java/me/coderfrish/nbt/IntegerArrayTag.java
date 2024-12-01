package me.coderfrish.nbt;

import java.io.DataOutput;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

@SuppressWarnings("all")
public record IntegerArrayTag(String key, int[] value) implements ITagBase, Iterable<Integer> {
    public IntegerArrayTag(int[] array) {
        this("", array);
    }

    @Override
    public void write(DataOutput output) throws Exception {
        if (value == null) {
            output.writeInt(0);
            return;
        }

        output.writeInt(value.length);
        for (Integer item : value) {
            output.writeInt(item);
        }
    }

    @Override
    public TagType type() {
        return TagType.INT_ARRAY;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public int[] value() {
        return value;
    }

    @Override
    public Iterator<Integer> iterator() {
        return Arrays.stream(value).iterator();
    }

    public int get(int index) {
        return value[index];
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        for (int i : value) {
            action.accept(i);
        }
    }

    @Override
    public String toString() {
        return "ints" + Arrays.toString(value);
    }
}
