package xyz.frish2021.nbt.primitive.number;

import xyz.frish2021.nbt.primitive.PrimitiveTag;
import xyz.frish2021.nbt.tag.TagType;

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
