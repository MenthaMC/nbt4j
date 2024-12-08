package xyz.frish2021.nbt.primitive;

import xyz.frish2021.nbt.api.Primitive;
import xyz.frish2021.nbt.exception.NBTWriteException;

public class PrimitiveTag<V> implements Primitive<V> {
    private V value;

    public PrimitiveTag(V value) {
        if (value == null) {
            throw new NBTWriteException("The primitive type cannot be null.");
        }

        this.value = value;
    }

    @Override
    public Primitive<V> value(V value) {
        this.value = value;
        return this;
    }

    @Override
    public V value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value());
    }
}
