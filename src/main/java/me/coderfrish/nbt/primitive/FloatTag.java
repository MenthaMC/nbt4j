package me.coderfrish.nbt.primitive;

import me.coderfrish.nbt.PrimitiveTag;

import java.io.DataOutput;
import java.util.Locale;

public class FloatTag extends PrimitiveTag<Float> {
    public FloatTag(String key, Float value) {
        super(key, value);
    }

    public FloatTag(Float value) {
        super("", value);
    }

    @Override
    public Float value() {
        return value;
    }

    @Override
    public void write(DataOutput output) throws Exception {
        output.writeFloat(value);
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public TagType type() {
        return TagType.FLOAT;
    }

    @Override
    public String toString() {
        String string = Float.toString(value);
        if (string.endsWith("F") || string.endsWith("f")) {
            return string.toUpperCase(Locale.ROOT);
        } else {
            return value + "F";
        }
    }
}
