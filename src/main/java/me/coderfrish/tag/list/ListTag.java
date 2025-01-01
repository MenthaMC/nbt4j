package me.coderfrish.tag.list;

import me.coderfrish.TagType;
import me.coderfrish.core.NBTException;
import me.coderfrish.ITag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ListTag implements Iterable<ITag>, ITag {
    private final List<ITag> list = new ArrayList<>();

    public ListTag add(ITag value) {
        list.add(value);
        return this;
    }

    public ListTag addAll(ListTag value) {
        for (ITag v : value) {
            list.add(v);
        }

        return this;
    }

    public ListTag remove(ITag value) {
        list.remove(value);

        return this;
    }

    public ITag get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(ITag value) {
        return list.contains(value);
    }

    public ITag getFirst() {
        if (list.isEmpty()) {
            throw new NBTException("This list is empty.");
        }

        return get(0);
    }

    public ListTag set(int index, ITag value) {
        list.set(index, value);
        return this;
    }

    public ITag getLast() {
        return get(size());
    }

    @Override
    public Iterator<ITag> iterator() {
        return list.iterator();
    }

    @Override
    public void forEach(Consumer<? super ITag> action) {
        for (ITag v : this) {
            action.accept(v);
        }
    }

    @Override
    public Spliterator<ITag> spliterator() {
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
        for (ITag v : this) {
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
