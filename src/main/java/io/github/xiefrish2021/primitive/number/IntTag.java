package io.github.xiefrish2021.primitive.number;

import io.github.xiefrish2021.primitive.PrimitiveTag;
import io.github.xiefrish2021.tag.TagType;

public class IntTag extends PrimitiveTag<Integer> {
    public IntTag(Integer value) {
        super(value);
    }

    @Override
    public TagType type() {
        return TagType.INT;
    }
}
