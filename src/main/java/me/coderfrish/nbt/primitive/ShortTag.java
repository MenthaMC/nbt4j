package me.coderfrish.nbt.primitive;

import me.coderfrish.nbt.PrimitiveTag;

import java.io.DataOutput;

public class ShortTag extends PrimitiveTag<Short> {
    public ShortTag(String key, Short value) {
        super(key, value);
    }

    public ShortTag(Short value) {
        super("", value);
    }

    @Override
    public Short value() {
        return value;
    }

    @Override
    public void write(DataOutput output) throws Exception {
        output.writeShort(value);
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public TagType type() {
        return TagType.SHORT;
    }

    @Override
    public String toString() {
        return value + "S";
    }
}
