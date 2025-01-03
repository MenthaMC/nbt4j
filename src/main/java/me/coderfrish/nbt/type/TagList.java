package me.coderfrish.nbt.type;

import me.coderfrish.nbt.type.element.NBTElement;

import java.util.*;

public class TagList implements Iterable<NBTElement> {
    private final Iterator<NBTElement> iterator;
    private final List<Object> list;

    public TagList(List<Object> list) {
        this.list = list;
        this.iterator = new ListIterator(list);
    }

    public TagList() {
        this(new ArrayList<>());
    }

    public int size() {
        return list.size();
    }

    public TagList add(Object value) {
        list.add(value);
        return this;
    }

    public NBTElement get(int index) {
        return new NBTElement(list.get(index));
    }

    public NBTElement getFirst() {
        return get(0);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<NBTElement> iterator() {
        return iterator;
    }

    @Override
    public Spliterator<NBTElement> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED);
    }

    private static final class ListIterator implements Iterator<NBTElement> {
        private final List<Object> array;
        private int currentIndex = 0;

        public ListIterator(List<Object> array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < array.size();
        }

        @Override
        public NBTElement next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return new NBTElement(array.get(currentIndex++));
        }
    }
}
