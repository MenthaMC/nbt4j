package me.coderfrish.nbt.primitive;

import me.coderfrish.nbt.PrimitiveTag;

import java.io.DataOutput;

public class StringTag extends PrimitiveTag<String> {
    public StringTag(String key, String value) {
        super(key, value);
    }

    public StringTag(String value) {
        this("", value);
    }

    @Override
    public void write(DataOutput output) throws Exception {
        output.writeUTF(value);
    }

    @Override
    public String value() {
        return this.value;
    }

    @Override
    public String key() {
        return this.key;
    }

    @Override
    public TagType type() {
        return TagType.STRING;
    }

    @Override
    public String toString() {
        return value;
    }
}
