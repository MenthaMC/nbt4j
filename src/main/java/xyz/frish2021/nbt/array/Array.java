package xyz.frish2021.nbt.array;

import xyz.frish2021.nbt.tag.ITag;

public interface Array<V> extends ITag, Iterable<V> {
    int size();

    V get(int index);
}
