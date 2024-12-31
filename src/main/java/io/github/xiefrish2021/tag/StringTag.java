package io.github.xiefrish2021.tag;

import io.github.xiefrish2021.ITag;
import io.github.xiefrish2021.TagType;
import io.github.xiefrish2021.core.NBTException;

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
