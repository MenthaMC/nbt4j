package me.coderfrish.nbt.type.primitive;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.ElementTag;

public class ByteTag extends ElementTag {
    private final byte value;

    public ByteTag(byte value) {
        this.value = value;
    }

    @Override
    public byte getAsByte() {
        return value;
    }

    @Override
    public TagType type() {
        return TagType.BYTE;
    }
}
