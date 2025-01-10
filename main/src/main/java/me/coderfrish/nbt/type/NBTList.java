package me.coderfrish.nbt.type;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class NBTList implements Iterable<NBTPrimitive> {
    private final List<NBTPrimitive> list;

    public NBTList() {
        this.list = new ArrayList<>();
    }

    public NBTList add(Object value) {
        this.list.add(new NBTPrimitive(value));
        return this;
    }

    public NBTPrimitive get(int index) {
        return this.list.get(index);
    }

    public NBTPrimitive getLast() {
        return this.list.getLast();
    }

    public NBTPrimitive getFirst() {
        return this.list.getFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public Stream<NBTPrimitive> stream() {
        return list.stream();
    }

    @Override
    public Iterator<NBTPrimitive> iterator() {
        return list.iterator();
    }
}
