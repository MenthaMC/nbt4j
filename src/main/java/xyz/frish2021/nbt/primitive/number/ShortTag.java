package xyz.frish2021.nbt.primitive.number;

import xyz.frish2021.nbt.primitive.PrimitiveTag;
import xyz.frish2021.nbt.tag.TagType;

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
