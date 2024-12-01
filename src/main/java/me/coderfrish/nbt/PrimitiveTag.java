package me.coderfrish.nbt;

public abstract class PrimitiveTag<V> implements ITagBase {
    protected final String key;
    protected final V value;

    public PrimitiveTag(String key, V value) {
        this.key = key;
        this.value = value;
    }

    public abstract V value();
}
