package xyz.frish2021.nbt.primitive.number;

import xyz.frish2021.nbt.primitive.PrimitiveTag;
import xyz.frish2021.nbt.tag.TagType;

public class IntTag extends PrimitiveTag<Integer> {
    public IntTag(Integer value) {
        super(value);
    }

    @Override
    public TagType type() {
        return TagType.INT;
    }
}
