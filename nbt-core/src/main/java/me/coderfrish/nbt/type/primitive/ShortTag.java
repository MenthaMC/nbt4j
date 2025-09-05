package me.coderfrish.nbt.type.primitive;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.ElementTag;

public class ShortTag extends ElementTag {
    private final short value;

    public ShortTag(short value) {
        this.value = value;
    }

    @Override
    public short getAsShort() {
        return value;
    }

    @Override
    public TagType type() {
        return TagType.SHORT;
    }
}
