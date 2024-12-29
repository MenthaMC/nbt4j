package io.github.xiefrish2021.tag.array;

import io.github.xiefrish2021.ITag;
import io.github.xiefrish2021.NBTException;
import io.github.xiefrish2021.TagType;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.function.Consumer;

@SuppressWarnings("all")
public class LongArrayTag implements ITag, Iterable<Long> {
    private final long[] array;

    public LongArrayTag(long[] array) {
        if (array == null) {
            throw new NBTException("The array type cannot be null.");
        }

        this.array = array;
    }

    public LongArrayTag(int size) {
        this(new long[size]);
    }

    public int size() {
        return array.length;
    }

    @Override
    public @NotNull Iterator<Long> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return array.length > index;
            }

            @Override
            public Long next() {
                return array[index++];
            }
        };
    }

    public long get(int index) {
        return array[index];
    }

    public LongArrayTag set(int index, byte value) {
        array[index] = value;
        return this;
    }

    public void forEach(Consumer<? super Long> action) {
        for (long v : array) {
            action.accept(v);
        }
    }

    @Override
    public TagType type() {
        return TagType.LONG_ARRAY;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[L;");

        int i = 0;
        for (long value : this) {
            builder.append(value).append("L");
            if (i < size() - 1) {
                builder.append(", ");
            }

            i++;
        }
        builder.append("]");

        return builder.toString();
    }
}
