package me.coderfrish.nbt.type;

import java.util.*;

public class TagByteArray implements Iterable<Byte> {
    private final ByteArrayIterator iterator;
    private final byte[] array;

    public TagByteArray(byte[] array) {
        this.array = array;
        this.iterator = new ByteArrayIterator(array);
    }

    public int size() {
        return array.length;
    }

    public long get(int index) {
        return array[index];
    }

    public TagByteArray set(int index, byte value) {
        array[index] = value;
        return this;
    }

    @Override
    public Iterator<Byte> iterator() {
        return iterator;
    }

    @Override
    public Spliterator<Byte> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED);
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

    private static final class ByteArrayIterator implements Iterator<Byte> {
        private final byte[] array;
        private int currentIndex = 0;

        public ByteArrayIterator(byte[] array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < array.length;
        }

        @Override
        public Byte next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return array[currentIndex++];
        }
    }
}
