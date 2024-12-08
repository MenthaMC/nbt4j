package io.github.xiefrish2021.primitive.number;

import io.github.xiefrish2021.primitive.PrimitiveTag;
import io.github.xiefrish2021.tag.TagType;

public class DoubleTag extends PrimitiveTag<Double> {
    public DoubleTag(Double value) {
        super(value);
    }

    @Override
    public TagType type() {
        return TagType.DOUBLE;
    }

    @Override
    public String toString() {
        return super.toString() + "D";
    }
}
