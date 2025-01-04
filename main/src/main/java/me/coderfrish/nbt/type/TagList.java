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

    public boolean contains(Object o) {
        return list.contains(o);
    }

    public TagList add(Object value) {
        list.add(value);
        return this;
    }

    public boolean remove(Object o) {
        return list.remove(o);
    }

    public NBTElement get(int index) {
        return new NBTElement(list.get(index));
    }

    public NBTElement get(int index, Object defaultValue) {
        if (get(index) == null) return new NBTElement(defaultValue);
        return get(index);
    }

    public NBTElement getFirst() {
        return get(0);
    }

    public NBTElement getLatest() {
        return get(size() - 1);
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        int i = 0;
        for (NBTElement v : this) {
            if (v.getAsOrigin() instanceof String) {
                if (((String) v.getAsOrigin()).matches("[0-9A-Za-z_\\-.+]+")) {
                    return v.getAsOrigin().toString();
                }

                builder.append("\"").append(v.getAsOrigin()).append("\"");
            } else if (v.getAsOrigin() instanceof Long) {
                builder.append(v.getAsOrigin()).append("L");
            } else if (v.getAsOrigin() instanceof Float) {
                builder.append(v.getAsOrigin()).append("F");
            } else if (v.getAsOrigin() instanceof Double) {
                builder.append(v.getAsOrigin()).append("D");
            } else if (v.getAsOrigin() instanceof Byte) {
                builder.append(v.getAsOrigin()).append("B");
            } else if (v.getAsOrigin() instanceof Short) {
                builder.append(v.getAsOrigin()).append("S");
            } else {
                builder.append(v.getAsOrigin().toString());
            }
            if (i < list.size() - 1) {
                builder.append(", ");
            }

            i++;
        }
        builder.append(']');

        return builder.toString();
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
