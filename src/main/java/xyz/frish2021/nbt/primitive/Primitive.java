package xyz.frish2021.nbt.primitive;

import xyz.frish2021.nbt.tag.ITag;

public interface Primitive<V> extends ITag {
    V value();

    Primitive<V> value(V value);
}
