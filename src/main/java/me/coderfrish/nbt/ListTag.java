package me.coderfrish.nbt;

import java.io.DataOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("all")
public record ListTag<V extends ITagBase>(String key, List<V> list) implements ITagBase, Iterable<V> {
    public ListTag(String key) {
        this(key, new ArrayList<>());
    }

    public ListTag() {
        this("", new ArrayList<>());
    }

    @Override
    public TagType type() {
        return TagType.LIST;
    }

    @Override
    public void write(DataOutput output) throws Exception {
        if (list.isEmpty()) {
            output.write(TagType.END.ordinal());
            output.writeInt(0);
            return;
        }

        output.write(list.getFirst().type().ordinal());
        output.writeInt(list.size());
        for (V tag : list) {
            tag.write(output);
        }
    }

    public ListTag<V> addItem(V value) {
        list.add(value);
        return this;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(V o) {
        return list.contains(o);
    }

    public ListTag<V> remove(V value) {
        list.remove(value);
        return this;
    }

    public V get(int index) {
        return list.get(index);
    }

    @Override
    public String key() {
        return key;
    }

    /**
     * @deprecated
     * {#{@link ListTag#forEach(Consumer)}}
     * {#{@link ListTag#iterator()}}
     * <code>
     * ListTag<StringTag> root = ...;
     * for(StringTag item : root) {
     *     System.out.println(item.value());
     * }
     * </code>
     */
    @Override
    @Deprecated
    public List<V> list() {
        return list;
    }

    @Override
    public Iterator<V> iterator() {
        return list.iterator();
    }

    @Override
    public void forEach(Consumer<? super V> action) {
        for (V v : list) {
            action.accept(v);
        }
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
