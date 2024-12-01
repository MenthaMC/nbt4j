package me.coderfrish.nbt.primitive;

import me.coderfrish.nbt.PrimitiveTag;

import java.io.DataOutput;

public class IntegerTag extends PrimitiveTag<Integer> {
    public IntegerTag(String key, Integer value) {
        super(key, value);
    }

    public IntegerTag(Integer value) {
        super("", value);
    }

    @Override
    public void write(DataOutput output) throws Exception {
        output.writeInt(value);
    }

    @Override
    public Integer value() {
        return value;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public TagType type() {
        return TagType.INT;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
