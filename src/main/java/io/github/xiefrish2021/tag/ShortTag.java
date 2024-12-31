package io.github.xiefrish2021.tag;

import io.github.xiefrish2021.ITag;
import io.github.xiefrish2021.TagType;
import io.github.xiefrish2021.core.NBTException;

public record ShortTag(Short value) implements ITag {
    public ShortTag {
        if (value == null) {
            throw new NBTException("The primitive type cannot be null.");
        }
    }

    @Override
    public TagType type() {
        return TagType.SHORT;
    }

    @Override
    public String toString() {
        return value + "S";
    }
}
