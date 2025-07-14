package me.coderfrish.nbt.type.primitive;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.ElementTag;

public class StringTag extends ElementTag {
    private final String value;

    public StringTag(String value) {
        this.value = value;
    }

    @Override
    public String getAsString() {
        return value;
    }

    @Override
    public TagType type() {
        return TagType.STRING;
    }
}
