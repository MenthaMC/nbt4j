package me.coderfrish.nbt.type;

import java.util.*;

public class TagIntArray implements Iterable<Integer> {
    private final Iterator<Integer> iterator;
    private final int[] array;

    public TagIntArray(int[] array) {
        this.array = array;
        this.iterator = new IntArrayIterator(array);
    }

    public int size() {
        return array.length;
    }

    public int get(int index) {
        return array[index];
    }

    public TagIntArray set(int index, int value) {
        array[index] = value;
        return this;
    }

    @Override
    public Iterator<Integer> iterator() {
        return iterator;
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED);
    }

    private static final class IntArrayIterator implements Iterator<Integer> {
        private final int[] array;
        private int currentIndex = 0;

        public IntArrayIterator(int[] array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < array.length;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return array[currentIndex++];
        }
    }
}
