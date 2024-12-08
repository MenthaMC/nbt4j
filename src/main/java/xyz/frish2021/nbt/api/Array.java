package xyz.frish2021.nbt.api;

public interface Array<V> extends ITag, Iterable<V> {
    int size();

    V get(int index);
}
