package me.coderfrish.nbt.type.primitive;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.ElementTag;

public class DoubleTag extends ElementTag {
    private final double value;

    public DoubleTag(double value) {
        this.value = value;
    }

    @Override
    public double getAsDouble() {
        return value;
    }

    @Override
    public TagType type() {
        return TagType.DOUBLE;
    }
}
