package io.github.xiefrish2021.api;

public interface List<V extends ITag> extends ITag, Iterable<V> {
    List<V> add(V value);

    List<V> addAll(List<V> value);

    List<V> remove(V value);

    V get(int index);

    int size();

    boolean isEmpty();

    boolean contains(V value);

    V getFirst();

    V getLast();
}
