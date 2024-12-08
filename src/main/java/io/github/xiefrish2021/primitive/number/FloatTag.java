package io.github.xiefrish2021.primitive.number;

import io.github.xiefrish2021.primitive.PrimitiveTag;
import io.github.xiefrish2021.tag.TagType;

public class FloatTag extends PrimitiveTag<Float> {
    public FloatTag(Float value) {
        super(value);
    }

    @Override
    public TagType type() {
        return TagType.FLOAT;
    }

    @Override
    public String toString() {
        return super.toString() + "F";
    }
}
