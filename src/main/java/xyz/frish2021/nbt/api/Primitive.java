package xyz.frish2021.nbt.api;

public interface Primitive<V> extends ITag {
    V value();

    Primitive<V> value(V value);
}
