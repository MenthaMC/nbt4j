package io.github.xiefrish2021.tag.compound;

import io.github.xiefrish2021.NBTException;
import io.github.xiefrish2021.tag.array.ByteArrayTag;
import io.github.xiefrish2021.tag.array.IntArrayTag;
import io.github.xiefrish2021.tag.array.LongArrayTag;
import io.github.xiefrish2021.tag.list.ListTag;
import io.github.xiefrish2021.tag.*;
import io.github.xiefrish2021.ITag;

public record NBTElement(ITag tag) {
    @SuppressWarnings({"unchecked", "unused"})
    public <V extends ITag> ListTag<V> getAsList( Class<V> type) {
        try {
            return (ListTag<V>) tag;
        } catch (ClassCastException exception) {
            throw new NBTException("Value is not a list");
        }
    }

    public LongArrayTag getAsLongArray() {
        try {
            return ((LongArrayTag) tag);
        } catch (ClassCastException exception) {
            throw new NBTException("Value is not a long array");
        }
    }

    public ByteArrayTag getAsByteArray() {
        try {
            return ((ByteArrayTag) tag);
        } catch (ClassCastException exception) {
            throw new NBTException("Value is not a byte array");
        }
    }

    public IntArrayTag getAsIntArray() {
        try {
            return ((IntArrayTag) tag);
        } catch (ClassCastException exception) {
            throw new NBTException("Value is not a int array");
        }
    }

    public short getAsShort() {
        try {
            return ((ShortTag) tag).value();
        } catch (ClassCastException exception) {
            throw new NBTException("Value is not a short");
        }
    }

    public long getAsLong() {
        try {
            return ((LongTag) tag).value();
        } catch (ClassCastException exception) {
            throw new NBTException("Value is not a long");
        }
    }

    public int getAsInt() {
        try {
            return ((IntTag) tag).value();
        } catch (ClassCastException exception) {
            throw new NBTException("Value is not a int");
        }
    }

    public byte getAsByte() {
        try {
            return ((ByteTag) tag).value();
        } catch (ClassCastException exception) {
            throw new NBTException("Value is not a byte");
        }
    }

    public double getAsDouble() {
        try {
            return ((DoubleTag) tag).value();
        } catch (ClassCastException exception) {
            throw new NBTException("Value is not a double");
        }
    }

    public float getAsFloat() {
        try {
            return ((FloatTag) tag).value();
        } catch (ClassCastException exception) {
            throw new NBTException("Value is not a float");
        }
    }

    public String getAsString() {
        try {
            return ((StringTag) tag).value();
        } catch (ClassCastException exception) {
            throw new NBTException("Value is not a string");
        }
    }

    public ITag asTag() {
        return tag;
    }
}
