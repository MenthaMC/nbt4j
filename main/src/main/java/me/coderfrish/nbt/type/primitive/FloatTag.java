package me.coderfrish.nbt.type.primitive;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.ElementTag;

public class FloatTag extends ElementTag {
    private final float value;

    public FloatTag(float value) {
        this.value = value;
    }

    @Override
    public float getAsFloat() {
        return value;
    }

    @Override
    public TagType type() {
        return TagType.FLOAT;
    }
}
