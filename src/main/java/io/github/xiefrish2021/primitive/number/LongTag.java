package io.github.xiefrish2021.primitive.number;

import io.github.xiefrish2021.primitive.PrimitiveTag;
import io.github.xiefrish2021.tag.TagType;

public class LongTag extends PrimitiveTag<Long> {
    public LongTag(Long value) {
        super(value);
    }

    @Override
    public TagType type() {
        return TagType.LONG;
    }

    @Override
    public String toString() {
        return super.toString() + "L";
    }
}
