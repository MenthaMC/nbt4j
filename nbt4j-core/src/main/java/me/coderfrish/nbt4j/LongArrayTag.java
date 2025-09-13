package me.coderfrish.nbt4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LongArrayTag extends ElementTag {
    private long[] array;

    public LongArrayTag(long[] array) {
        this.array = array;
    }

    @Override
    public long[] getAsLongArray() {
        return this.array;
    }

    @Override
    public TagType type() {
        return TagType.LONG_ARRAY;
    }

    @Override
    void write(DataOutput output) throws IOException {
        output.writeInt(array.length);
        for (long item : array) {
            output.writeLong(item);
        }
    }

    @Override
    void read(DataInput input) throws IOException {
        int size = input.readInt();
        array = new long[size];
        for (int i = 0; i < size; i++)
            array[i] = input.readLong();
    }
}
