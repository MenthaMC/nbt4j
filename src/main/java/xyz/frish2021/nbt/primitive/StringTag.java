package xyz.frish2021.nbt.primitive;

import xyz.frish2021.nbt.tag.TagType;

public class StringTag extends PrimitiveTag<String> {
    public StringTag(String value) {
        super(value);
    }

    @Override
    public TagType type() {
        return TagType.STRING;
    }

    @Override
    public String toString() {
        if (super.toString().matches("[0-9A-Za-z_\\-.+]+")) {
            return super.toString();
        }

        return "\"" + super.toString() + "\"";
    }
}
