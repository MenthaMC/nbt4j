package io.github.xiefrish2021.tag.list;

import org.jetbrains.annotations.NotNull;
import io.github.xiefrish2021.NBTException;
import io.github.xiefrish2021.ITag;
import io.github.xiefrish2021.TagType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ListTag<V extends ITag> implements Iterable<V>, ITag {
    private final java.util.List<V> list = new ArrayList<>();

    public ListTag<V> add(V value) {
        list.add(value);
        return this;
    }

    public ListTag<V> addAll(ListTag<V> value) {
        for (V v : value) {
            list.add(v);
        }

        return this;
    }

    public ListTag<V> remove(V value) {
        list.remove(value);

        return this;
    }

    public V get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(V value) {
        return list.contains(value);
    }

    public V getFirst() {
        if (list.isEmpty()) {
            throw new NBTException("This list is empty.");
        }

        return get(0);
    }

    public V getLast() {
        return get(size());
    }

    @Override
    public @NotNull Iterator<V> iterator() {
        return list.iterator();
    }

    @Override
    public void forEach(Consumer<? super V> action) {
        for (V v : this) {
            action.accept(v);
        }
    }

    @Override
    public Spliterator<V> spliterator() {
        return list.spliterator();
    }

    @Override
    public TagType type() {
        return TagType.LIST;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        int i = 0;
        for (V v : this) {
            builder.append(v.toString());
            if (i < list.size() - 1) {
                builder.append(", ");
            }

            i++;
        }
        builder.append(']');

        return builder.toString();
    }
}
