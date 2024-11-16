package me.coderfrish.nbt.types;

import me.coderfrish.nbt.ITagBase;
import me.coderfrish.nbt.TagType;

import java.io.DataOutputStream;

@SuppressWarnings("all")
public record TagLong(long value) implements ITagBase {
    @Override
    public void write(DataOutputStream out) throws Exception {
        out.writeLong(value);
    }

    @Override
    public TagType getType() {
        return TagType.LONG;
    }

    @Override
    public String toString() {
        return "TagLong{" +
                "value=" + value +
                '}';
    }
}
