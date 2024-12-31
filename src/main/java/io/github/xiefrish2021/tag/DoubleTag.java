package io.github.xiefrish2021.tag;

import io.github.xiefrish2021.ITag;
import io.github.xiefrish2021.TagType;
import io.github.xiefrish2021.core.NBTException;

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
