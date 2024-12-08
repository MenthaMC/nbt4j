package io.github.xiefrish2021.primitive;

import io.github.xiefrish2021.tag.TagType;

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
