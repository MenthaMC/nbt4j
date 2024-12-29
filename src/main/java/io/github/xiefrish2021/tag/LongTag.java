package io.github.xiefrish2021.tag;

import io.github.xiefrish2021.ITag;
import io.github.xiefrish2021.TagType;
import io.github.xiefrish2021.NBTException;

public class LongTag implements ITag {
    private Long value;

    public LongTag(Long value) {
        if (value == null) {
            throw new NBTException("The primitive type cannot be null.");
        }

        this.value = value;
    }

    public LongTag value(Long value) {
        this.value = value;
        return this;
    }

    public Long value() {
        return value;
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
