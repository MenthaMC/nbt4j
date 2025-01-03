package me.coderfrish.nbt.type;

import java.util.*;

public class TagLongArray implements Iterable<Long> {
    private final Iterator<Long> iterator;
    private final long[] array;

    public TagLongArray(long[] array) {
        this.array = array;
        this.iterator = new LongArrayIterator(array);
    }

    public int size() {
        return array.length;
    }

    public long get(int index) {
        return array[index];
    }

    public TagLongArray set(int index, long value) {
        array[index] = value;
        return this;
    }

    @Override
    public Iterator<Long> iterator() {
        return iterator;
    }

    @Override
    public Spliterator<Long> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED);
    }

    private static final class LongArrayIterator implements Iterator<Long> {
        private final long[] array;
        private int currentIndex = 0;

        public LongArrayIterator(long[] array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < array.length;
        }

        @Override
        public Long next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return array[currentIndex++];
        }
    }
}
