package me.coderfrish.nbt4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public abstract class ElementTag {
    public String getAsString() {
        throw new IllegalStateException("Not a String Tag.");
    }

    public Number getAsNumber() {
        throw new IllegalStateException("Not a Number Tag.");
    }

    public CompoundTag getAsCompound() {
        throw new IllegalStateException("Not a Compound Tag.");
    }

    public abstract TagType type();

    abstract void write(DataOutput output) throws IOException;

    abstract void read(DataInput input) throws IOException;
}
