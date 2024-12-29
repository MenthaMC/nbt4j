package io.github.xiefrish2021.tag;

import io.github.xiefrish2021.ITag;
import io.github.xiefrish2021.TagType;
import io.github.xiefrish2021.NBTException;

public class DoubleTag implements ITag {
    private Double value;

    public DoubleTag(Double value) {
        if (value == null) {
            throw new NBTException("The primitive type cannot be null.");
        }

        this.value = value;
    }

    public DoubleTag value(Double value) {
        this.value = value;
        return this;
    }

    public Double value() {
        return value;
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
