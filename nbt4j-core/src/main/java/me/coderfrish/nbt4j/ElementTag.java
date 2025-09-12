package me.coderfrish.nbt4j;

public abstract class ElementTag {
    public String getAsString() {
        throw new IllegalArgumentException("This isn`t a string tag.");
    }

    public Number getAsNumber() {
        throw new IllegalArgumentException("This isn`t a number tag.");
    }

    public CompoundTag getAsCompound() {
        throw new IllegalArgumentException("This isn`t a compound tag.");
    }

    public ListTag getAsList() {
        throw new IllegalArgumentException("This isn`t a list tag.");
    }

    public byte[] getAsByteArray() {
        throw new IllegalArgumentException("This isn`t a byte array tag.");
    }

    public long[] getAsLongArray() {
        throw new IllegalArgumentException("This isn`t a long array tag.");
    }

    public int[] getAsIntArray() {
        throw new IllegalArgumentException("This isn`t a int array tag.");
    }
}
