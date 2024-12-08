package io.github.xiefrish2021.primitive.number;

import io.github.xiefrish2021.primitive.PrimitiveTag;
import io.github.xiefrish2021.tag.TagType;

public class ByteTag extends PrimitiveTag<Byte> {
    public ByteTag(Byte value) {
        super(value);
    }

    @Override
    public TagType type() {
        return TagType.BYTE;
    }

    @Override
    public String toString() {
        return super.toString() + "B";
    }
}
