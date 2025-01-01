package me.coderfrish.tag.array;

import me.coderfrish.ITag;
import me.coderfrish.core.NBTException;
import me.coderfrish.TagType;

import java.util.Iterator;
import java.util.function.Consumer;

@SuppressWarnings("all")
public class IntArrayTag implements ITag, Iterable<Integer> {
    private final int[] array;

    public IntArrayTag(int[] array) {
        if (array == null) {
            throw new NBTException("The array type cannot be null.");
        }

        this.array = array;
    }

    public IntArrayTag(int size) {
        this(new int[size]);
    }

    public int size() {
        return array.length;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return array.length > index;
            }

            @Override
            public Integer next() {
                return array[index++];
            }
        };
    }

    public int get(int index) {
        return array[index];
    }

    public IntArrayTag set(int index, byte value) {
        array[index] = value;
        return this;
    }

    public void forEach(Consumer<? super Integer> action) {
        for (int v : array) {
            action.accept(v);
        }
    }

    @Override
    public TagType type() {
        return TagType.INT_ARRAY;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[I;");

        int i = 0;
        for (int value : this) {
            builder.append(value);
            if (i < size() - 1) {
                builder.append(", ");
            }

            i++;
        }
        builder.append("]");

        return builder.toString();
    }
}
