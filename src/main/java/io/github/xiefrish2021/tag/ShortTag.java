package io.github.xiefrish2021.tag;

import io.github.xiefrish2021.ITag;
import io.github.xiefrish2021.TagType;
import io.github.xiefrish2021.NBTException;

@SuppressWarnings("all")
public class ShortTag implements ITag {
    private Short value;

    public ShortTag(Short value) {
        if (value == null) {
            throw new NBTException("The primitive type cannot be null.");
        }

        this.value = value;
    }

    public ShortTag value(Short value) {
        this.value = value;
        return this;
    }

    public Short value() {
        return value;
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
