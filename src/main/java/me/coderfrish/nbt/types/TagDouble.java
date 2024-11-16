package me.coderfrish.nbt.types;

import me.coderfrish.nbt.ITagBase;
import me.coderfrish.nbt.TagType;

import java.io.DataOutputStream;

@SuppressWarnings("all")
public record TagDouble(double value) implements ITagBase {
    @Override
    public void write(DataOutputStream out) throws Exception {
        out.writeDouble(value);
    }

    @Override
    public TagType getType() {
        return TagType.DOUBLE;
    }

    @Override
    public String toString() {
        return "TagDouble{" +
                "value=" + value +
                '}';
    }
}
