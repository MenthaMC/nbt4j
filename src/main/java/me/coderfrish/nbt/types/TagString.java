package me.coderfrish.nbt.types;

import me.coderfrish.nbt.ITagBase;
import me.coderfrish.nbt.TagType;

import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("all")
public record TagString(String value) implements ITagBase {
    @Override
    public void write(DataOutputStream out) throws Exception {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        out.writeChar(bytes.length);
        out.write(bytes);
    }

    @Override
    public TagType getType() {
        return TagType.STRING;
    }

    @Override
    public String toString() {
        return "TagString{" +
                "value='" + value + '\'' +
                '}';
    }
}
