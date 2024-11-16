package me.coderfrish.nbt.types;

import me.coderfrish.nbt.ITagBase;
import me.coderfrish.nbt.TagType;

import java.io.DataOutputStream;

@SuppressWarnings("all")
public record TagByte(byte value) implements ITagBase {
    @Override
    public void write(DataOutputStream out) throws Exception {
        out.writeByte(value);
    }

    @Override
    public TagType getType() {
        return TagType.BYTE;
    }

    @Override
    public String toString() {
        return "TagByte{" +
                "value=" + value +
                '}';
    }
}
