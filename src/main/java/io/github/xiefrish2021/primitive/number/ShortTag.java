package io.github.xiefrish2021.primitive.number;

import io.github.xiefrish2021.primitive.PrimitiveTag;
import io.github.xiefrish2021.tag.TagType;

public class ShortTag extends PrimitiveTag<Short> {
    public ShortTag(Short value) {
        super(value);
    }

    @Override
    public TagType type() {
        return TagType.SHORT;
    }

    @Override
    public String toString() {
        return super.toString() + "S";
    }
}
