package me.coderfrish.nbt.type;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.iterator.*;

public abstract class ElementTag {
    public String getAsString() {
        throw new IllegalArgumentException("This isn`t a string tag.");
    }

    public int getAsInt() {
        throw new IllegalArgumentException("This isn`t a int tag.");
    }

    public short getAsShort() {
        throw new IllegalArgumentException("This isn`t a short tag.");
    }

    public float getAsFloat() {
        throw new IllegalArgumentException("This isn`t a float tag.");
    }

    public double getAsDouble() {
        throw new IllegalArgumentException("This isn`t a double tag.");
    }

    public byte getAsByte() {
        throw new IllegalArgumentException("This isn`t a byte tag.");
    }

    public long getAsLong() {
        throw new IllegalArgumentException("This isn`t a long tag.");
    }

    public CompoundTag getAsCompound() {
        throw new IllegalArgumentException("This isn`t a compound tag.");
    }

    public IntArrayTag getAsIntArray() {
        throw new IllegalArgumentException("This isn`t a int array tag.");
    }

    public LongArrayTag getAsLongArray() {
        throw new IllegalArgumentException("This isn`t a long array tag.");
    }

    public ByteArrayTag getAsByteArray() {
        throw new IllegalArgumentException("This isn`t a byte array tag.");
    }

    public ListTag getAsList() {
        throw new IllegalArgumentException("This isn`t a list tag.");
    }

    public abstract TagType type();
}
