package io.github.xiefrish2021.api;

public interface Array<V> extends ITag, Iterable<V> {
    int size();

    V get(int index);
}
