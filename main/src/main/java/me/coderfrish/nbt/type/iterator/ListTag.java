package me.coderfrish.nbt.type.iterator;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.ElementTag;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListTag extends ElementTag implements Iterable<ElementTag> {
    private final List<ElementTag> list;

    public ListTag() {
        this.list = new LinkedList<>();
    }

    public int size() {
        return list.size();
    }

    public ElementTag getFirst() {
        return list.getFirst();
    }

    public ElementTag getLast() {
        return list.getLast();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public ElementTag get(int i) {
        return list.get(i);
    }

    public ListTag set(int i, ElementTag value) {
        list.set(i, value);
        return this;
    }

    public ListTag add(ElementTag value) {
        list.add(value);
        return this;
    }

    @Override
    public ListTag getAsList() {
        return this;
    }

    @Override
    public TagType type() {
        return TagType.LIST;
    }

    @Override
    public Iterator<ElementTag> iterator() {
        return list.iterator();
    }
}
