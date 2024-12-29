package io.github.xiefrish2021.tag;

import io.github.xiefrish2021.ITag;
import io.github.xiefrish2021.TagType;
import io.github.xiefrish2021.NBTException;

public class FloatTag implements ITag {
    private Float value;

    public FloatTag(Float value) {
        if (value == null) {
            throw new NBTException("The primitive type cannot be null.");
        }

        this.value = value;
    }

    public FloatTag value(Float value) {
        this.value = value;
        return this;
    }

    public Float value() {
        return value;
    }
    @Override
    public TagType type() {
        return TagType.FLOAT;
    }

    @Override
    public String toString() {
        return value + "F";
    }
}
