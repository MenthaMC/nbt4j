package xyz.frish2021.nbt.primitive.number;

import xyz.frish2021.nbt.primitive.PrimitiveTag;
import xyz.frish2021.nbt.tag.TagType;

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
