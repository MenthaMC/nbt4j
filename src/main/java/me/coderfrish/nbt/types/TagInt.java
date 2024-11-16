package me.coderfrish.nbt.types;

import me.coderfrish.nbt.ITagBase;
import me.coderfrish.nbt.TagType;

import java.io.DataOutputStream;

@SuppressWarnings("all")
public record TagInt(int value) implements ITagBase {
    @Override
    public void write(DataOutputStream out) throws Exception {
        out.writeInt(value);
    }

    @Override
    public TagType getType() {
        return TagType.INT;
    }

    @Override
    public String toString() {
        return "TagInt{" +
                "value=" + value +
                '}';
    }
}
