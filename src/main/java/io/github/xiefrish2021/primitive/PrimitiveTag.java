package io.github.xiefrish2021.primitive;

import io.github.xiefrish2021.api.ITag;
import io.github.xiefrish2021.exception.NBTWriteException;

public class PrimitiveTag<V> implements ITag {
    private V value;

    public PrimitiveTag(V value) {
        if (value == null) {
            throw new NBTWriteException("The primitive type cannot be null.");
        }

        this.value = value;
    }

    public PrimitiveTag<V> value(V value) {
        this.value = value;
        return this;
    }

    public V value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value());
    }
}
