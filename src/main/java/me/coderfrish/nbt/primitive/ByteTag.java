package me.coderfrish.nbt.primitive;

import me.coderfrish.nbt.PrimitiveTag;

import java.io.DataOutput;

public class ByteTag extends PrimitiveTag<Byte> {
    public ByteTag(String key, Byte value) {
        super(key, value);
    }

    public ByteTag(Byte value) {
        super("", value);
    }

    @Override
    public void write(DataOutput output) throws Exception {
        output.writeByte(value);
    }

    @Override
    public Byte value() {
        return value;
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
        return value + "B";
    }
}
