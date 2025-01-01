package me.coderfrish.tag;

import me.coderfrish.ITag;
import me.coderfrish.TagType;
import me.coderfrish.core.NBTException;

public record ByteTag(Byte value) implements ITag {
    public ByteTag {
        if (value == null) {
            throw new NBTException("The primitive type cannot be null.");
        }
    }

    @Override
    public TagType type() {
        return TagType.BYTE;
    }

    @Override
    public String toString() {
        return value + "B";
    }
}
