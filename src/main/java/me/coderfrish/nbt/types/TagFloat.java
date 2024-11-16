package me.coderfrish.nbt.types;

import me.coderfrish.nbt.ITagBase;
import me.coderfrish.nbt.TagType;

import java.io.DataOutputStream;

@SuppressWarnings("all")
public record TagFloat(float value) implements ITagBase {
    @Override
    public void write(DataOutputStream out) throws Exception {
        out.writeFloat(value);
    }

    @Override
    public TagType getType() {
        return TagType.FLOAT;
    }

    @Override
    public String toString() {
        return "TagFloat{" +
                "value=" + value +
                '}';
    }
}
