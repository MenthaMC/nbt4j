package me.coderfrish.tag.compound;

import me.coderfrish.core.NBTException;
import me.coderfrish.tag.*;
import me.coderfrish.tag.array.ByteArrayTag;
import me.coderfrish.tag.array.IntArrayTag;
import me.coderfrish.tag.array.LongArrayTag;
import me.coderfrish.tag.list.ListTag;
import me.coderfrish.ITag;

public record NBTElement(ITag tag) {
    public ListTag getAsList() {
        try {
            return (ListTag) tag;
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

    public ITag getAsTag() {
        return tag;
    }
}
