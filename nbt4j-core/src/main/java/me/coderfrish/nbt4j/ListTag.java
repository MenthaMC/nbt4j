package me.coderfrish.nbt4j;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTag extends ElementTag implements Iterable<ElementTag> {
    private final List<ElementTag> tags = new CopyOnWriteArrayList<>();

    public void add(ElementTag value) {
        tags.add(value);
    }

    public void add(int index, ElementTag value) {
        tags.add(index, value);
    }

    public void addProperty(String value) {
        tags.add(new StringTag(value));
    }

    public void addProperty(int index, String value) {
        tags.add(index, new StringTag(value));
    }

    public void addProperty(Number value) {
        tags.add(new NumberTag(value));
    }

    public void addProperty(int index, Number value) {
        tags.add(index, new NumberTag(value));
    }

    public ElementTag get(int index) {
        return tags.get(index);
    }

    public boolean remove(ElementTag tag) {
        return tags.remove(tag);
    }

    public ElementTag remove(int index) {
        return tags.remove(index);
    }

    public ElementTag replace(int index, ElementTag newValue) {
        return tags.set(index, newValue);
    }

    public int size() {
        return tags.size();
    }

    public boolean isEmpty() {
        return tags.isEmpty();
    }

    public boolean isNotEmpty() {
        return !this.isEmpty();
    }

    @Override
    public ListTag getAsList() {
        return this;
    }

    @Override
    public Iterator<ElementTag> iterator() {
        return tags.iterator();
    }
}
