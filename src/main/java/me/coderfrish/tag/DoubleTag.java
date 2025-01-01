package me.coderfrish.tag;

import me.coderfrish.ITag;
import me.coderfrish.TagType;
import me.coderfrish.core.NBTException;

public record DoubleTag(Double value) implements ITag {
    public DoubleTag {
        if (value == null) {
            throw new NBTException("The primitive type cannot be null.");
        }
    }

    @Override
    public TagType type() {
        return TagType.DOUBLE;
    }

    @Override
    public String toString() {
        return value + "D";
    }
}
