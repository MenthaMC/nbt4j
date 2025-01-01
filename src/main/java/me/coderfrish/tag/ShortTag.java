package me.coderfrish.tag;

import me.coderfrish.ITag;
import me.coderfrish.TagType;
import me.coderfrish.core.NBTException;

public record ShortTag(Short value) implements ITag {
    public ShortTag {
        if (value == null) {
            throw new NBTException("The primitive type cannot be null.");
        }
    }

    @Override
    public TagType type() {
        return TagType.SHORT;
    }

    @Override
    public String toString() {
        return value + "S";
    }
}
