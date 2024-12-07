package xyz.frish2021.nbt.primitive.number;

import xyz.frish2021.nbt.primitive.PrimitiveTag;
import xyz.frish2021.nbt.tag.TagType;

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
