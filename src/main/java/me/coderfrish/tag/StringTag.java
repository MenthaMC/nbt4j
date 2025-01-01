package me.coderfrish.tag;

import me.coderfrish.ITag;
import me.coderfrish.TagType;
import me.coderfrish.core.NBTException;

public record StringTag(String value) implements ITag {
    public StringTag {
        if (value == null) {
            throw new NBTException("The primitive type cannot be null.");
        }

    }

    @Override
    public TagType type() {
        return TagType.STRING;
    }

    @Override
    public String toString() {
        if (value.matches("[0-9A-Za-z_\\-.+]+")) {
            return value;
        }

        return "\"" + value + "\"";
    }
}
