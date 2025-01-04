package me.coderfrish.nbt.type.element;

import me.coderfrish.nbt.type.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class NBTElement {
    private final Object value;

    public NBTElement(Object value) {
        this.value = value;
    }

    public Object getAsOrigin() {
        return value;
    }

    public String getAsString() {
        return (String) value;
    }

    public int getAsInt() {
        return (int) value;
    }

    public long getAsLong() {
        return (long) value;
    }

    public float getAsFloat() {
        return (float) value;
    }

    public double getAsDouble() {
        return (double) value;
    }

    public boolean getAsBoolean() {
        byte value = (byte) this.value;
        if (value == 0) {
            return false;
        } else if (value == 1) {
            return true;
        } else {
            throw new RuntimeException("This type is not Boolean.");
        }
    }

    public short getAsShort() {
        return (short) value;
    }

    public byte getAsByte() {
        return (byte) value;
    }

    public TagIntArray getAsTagIntArray() {
        return (TagIntArray) value;
    }

    public TagLongArray getAsLongArray() {
        return (TagLongArray) value;
    }

    public TagByteArray getAsByteArray() {
        return (TagByteArray) value;
    }

    public TagList getAsList() {
        return (TagList) value;
    }

    public TagObject getAsObject() {
        return (TagObject) value;
    }
}
