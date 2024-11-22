package me.coderfrish.nbt.types;

import me.coderfrish.nbt.ITagBase;
import me.coderfrish.nbt.TagType;

import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;

import static me.coderfrish.nbt.util.CommonUtil.writeString;

@SuppressWarnings("all")
public record TagString(String value) implements ITagBase {
    @Override
    public void write(DataOutputStream out) throws Exception {
        writeString(out, value);
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
