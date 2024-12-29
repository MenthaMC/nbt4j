package io.github.xiefrish2021.tag;

import io.github.xiefrish2021.ITag;
import io.github.xiefrish2021.TagType;
import io.github.xiefrish2021.NBTException;

public class IntTag implements ITag {
    private Integer value;

    public IntTag(Integer value) {
        if (value == null) {
            throw new NBTException("The primitive type cannot be null.");
        }

        this.value = value;
    }

    public IntTag value(Integer value) {
        this.value = value;
        return this;
    }

    public Integer value() {
        return value;
    }

    @Override
    public TagType type() {
        return TagType.INT;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
