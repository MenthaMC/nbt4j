package me.coderfrish.tag;

import me.coderfrish.ITag;
import me.coderfrish.TagType;
import me.coderfrish.core.NBTException;

public record LongTag(Long value) implements ITag {
    public LongTag {
        if (value == null) {
            throw new NBTException("The primitive type cannot be null.");
        }
    }

    @Override
    public TagType type() {
        return TagType.LONG;
    }

    @Override
    public String toString() {
        return value + "L";
    }
}
