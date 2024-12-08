package io.github.xiefrish2021.api;

public interface Primitive<V> extends ITag {
    V value();

    Primitive<V> value(V value);
}
