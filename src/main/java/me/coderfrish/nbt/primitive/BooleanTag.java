package me.coderfrish.nbt.primitive;

import me.coderfrish.nbt.PrimitiveTag;

import java.io.DataOutput;

public class BooleanTag extends PrimitiveTag<Boolean> {
    public BooleanTag(String key, Boolean value) {
        super(key, value);
    }

    public BooleanTag(Boolean value) {
        super("", value);
    }

    @Override
    public Boolean value() {
        return value;
    }

    @Override
    public void write(DataOutput output) throws Exception {
        if (value) {
            output.write(1);
        } else {
            output.write(0);
        }
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public TagType type() {
        return TagType.BYTE;
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }
}
