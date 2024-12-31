package io.github.xiefrish2021.tag;

import io.github.xiefrish2021.ITag;
import io.github.xiefrish2021.TagType;
import io.github.xiefrish2021.core.NBTException;

public record LongTag(Long value) implements ITag {
    public LongTag {
        if (value == null) {
            throw new NBTException("The primitive type cannot be null.");
        }
    }

    @Override
    public TagType type() {
        return TagType.LONG;
    }

    @Override
    public String toString() {
        return value + "L";
    }
}
