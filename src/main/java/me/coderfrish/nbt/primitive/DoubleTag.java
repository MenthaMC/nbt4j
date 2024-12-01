package me.coderfrish.nbt.primitive;

import me.coderfrish.nbt.PrimitiveTag;

import java.io.DataOutput;

public class DoubleTag extends PrimitiveTag<Double> {
    public DoubleTag(String key, Double value) {
        super(key, value);
    }

    public DoubleTag(Double value) {
        super("", value);
    }

    @Override
    public Double value() {
        return value;
    }

    @Override
    public void write(DataOutput output) throws Exception {
        output.writeDouble(value);
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public TagType type() {
        return TagType.DOUBLE;
    }

    @Override
    public String toString() {
        return value + "D";
    }
}
