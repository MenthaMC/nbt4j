package me.coderfrish.nbt.types;

import me.coderfrish.nbt.ITagBase;
import me.coderfrish.nbt.TagType;

import java.io.DataOutputStream;

@SuppressWarnings("all")
public record TagShort(short value) implements ITagBase {
    @Override
    public void write(DataOutputStream out) throws Exception {
        out.writeShort(value);
    }

    @Override
    public TagType getType() {
        return TagType.SHORT;
    }

    @Override
    public String toString() {
        return "TagShort{" +
                "value=" + value +
                '}';
    }
}
