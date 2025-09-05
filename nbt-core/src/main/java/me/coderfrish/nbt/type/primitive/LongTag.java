package me.coderfrish.nbt.type.primitive;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.ElementTag;

public class LongTag extends ElementTag {
    private final long value;

    public LongTag(long value) {
        this.value = value;
    }

    @Override
    public long getAsLong() {
        return value;
    }

    @Override
    public TagType type() {
        return TagType.LONG;
    }
}
