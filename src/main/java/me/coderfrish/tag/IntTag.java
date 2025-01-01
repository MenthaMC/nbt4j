package me.coderfrish.tag;

import me.coderfrish.ITag;
import me.coderfrish.TagType;
import me.coderfrish.core.NBTException;

public record IntTag(Integer value) implements ITag {
    public IntTag {
        if (value == null) {
            throw new NBTException("The primitive type cannot be null.");
        }
    }

    @Override
    public TagType type() {
        return TagType.INT;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
