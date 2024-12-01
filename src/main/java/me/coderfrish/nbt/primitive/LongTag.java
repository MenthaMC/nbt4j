package me.coderfrish.nbt.primitive;

import me.coderfrish.nbt.PrimitiveTag;

import java.io.DataOutput;
import java.util.Locale;

public class LongTag extends PrimitiveTag<Long> {
    public LongTag(String key, Long value) {
        super(key, value);
    }

    public LongTag(Long value) {
        super("", value);
    }

    @Override
    public void write(DataOutput output) throws Exception {
        output.writeLong(value);
    }

    @Override
    public Long value() {
        return value;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public TagType type() {
        return TagType.LONG;
    }

    @Override
    public String toString() {
        String string = String.valueOf(value);
        if (string.endsWith("L") || string.endsWith("l")) {
            return string.toUpperCase(Locale.ROOT);
        } else {
            return string + "L";
        }
    }
}
