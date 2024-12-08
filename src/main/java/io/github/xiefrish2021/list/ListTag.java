package io.github.xiefrish2021.list;

import org.jetbrains.annotations.NotNull;
import io.github.xiefrish2021.api.List;
import io.github.xiefrish2021.exception.NBTWriteException;
import io.github.xiefrish2021.api.ITag;
import io.github.xiefrish2021.tag.TagType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ListTag<V extends ITag> implements List<V> {
    private final java.util.List<V> list = new ArrayList<>();

    @Override
    public List<V> add(V value) {
        list.add(value);
        return this;
    }

    @Override
    public List<V> addAll(List<V> value) {
        for (V v : value) {
            list.add(v);
        }

        return this;
    }

    @Override
    public List<V> remove(V value) {
        list.remove(value);

        return this;
    }

    @Override
    public V get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(V value) {
        return list.contains(value);
    }

    @Override
    public V getFirst() {
        if (list.isEmpty()) {
            throw new NBTWriteException("This list is empty.");
        }

        return get(0);
    }

    @Override
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
