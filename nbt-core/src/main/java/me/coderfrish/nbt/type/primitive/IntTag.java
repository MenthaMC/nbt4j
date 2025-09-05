package me.coderfrish.nbt.type.primitive;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.ElementTag;

public class IntTag extends ElementTag {
    private final int value;

    public IntTag(int value) {
        this.value = value;
    }

    @Override
    public int getAsInt() {
        return value;
    }

    @Override
    public TagType type() {
        return TagType.INT;
    }
}
