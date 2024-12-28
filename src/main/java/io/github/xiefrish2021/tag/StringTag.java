package io.github.xiefrish2021.tag;

import io.github.xiefrish2021.ITag;
import io.github.xiefrish2021.TagType;
import io.github.xiefrish2021.exception.NBTWriteException;

@SuppressWarnings("all")
public class StringTag implements ITag {
    private String value;

    public StringTag(String value) {
        if (value == null) {
            throw new NBTWriteException("The primitive type cannot be null.");
        }

        this.value = value;
    }

    public StringTag value(String value) {
        this.value = value;
        return this;
    }

    public String value() {
        return value;
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
