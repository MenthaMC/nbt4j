package io.github.xiefrish2021.tag;

import io.github.xiefrish2021.ITag;
import io.github.xiefrish2021.TagType;
import io.github.xiefrish2021.NBTException;

public class ByteTag implements ITag {
    private Byte value;

    public ByteTag(Byte value) {
        if (value == null) {
            throw new NBTException("The primitive type cannot be null.");
        }

        this.value = value;
    }

    public ByteTag value(Byte value) {
        this.value = value;
        return this;
    }

    public Byte value() {
        return value;
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
