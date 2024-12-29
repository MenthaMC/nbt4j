package io.github.xiefrish2021.tag.array;

import io.github.xiefrish2021.ITag;
import io.github.xiefrish2021.NBTException;
import io.github.xiefrish2021.TagType;
import io.github.xiefrish2021.util.CommonUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;


public class ByteArrayTag implements ITag, Iterable<Byte> {
    private final byte[] array;

    public ByteArrayTag(byte[] array) {
        if (array == null) {
            throw new NBTException("The array type cannot be null.");
        }

        this.array = array;
    }

    public ByteArrayTag(int size) {
        this(new byte[size]);
    }

    public int size() {
        return array.length;
    }

    @Override
    public @NotNull Iterator<Byte> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return array.length > index;
            }

            @Override
            public Byte next() {
                return array[index++];
            }
        };
    }

    public byte get(int index) {
        return array[index];
    }

    public ByteArrayTag set(int index, byte value) {
        array[index] = value;
        return this;
    }

    public void forEach(Consumer<? super Byte> action) {
        for (byte v : array) {
            action.accept(v);
        }
    }

    @Override
    public TagType type() {
        return TagType.BYTE_ARRAY;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[B;");

        int i = 0;
        for (byte value : this) {
            builder.append(value).append("B");
            if (i < size() - 1) {
                builder.append(", ");
            }

            i++;
        }
        builder.append("]");

        return builder.toString();
    }
}
