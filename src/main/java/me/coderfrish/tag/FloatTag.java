package me.coderfrish.tag;

import me.coderfrish.ITag;
import me.coderfrish.TagType;
import me.coderfrish.core.NBTException;

public record FloatTag(Float value) implements ITag {
    public FloatTag {
        if (value == null) {
            throw new NBTException("The primitive type cannot be null.");
        }
    }

    @Override
    public TagType type() {
        return TagType.FLOAT;
    }

    @Override
    public String toString() {
        return value + "F";
    }
}
