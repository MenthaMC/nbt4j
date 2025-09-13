package me.coderfrish.nbt4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IntArrayTag extends ElementTag {
    private int[] array;

    public IntArrayTag(int[] array) {
        this.array = array;
    }

    @Override
    public int[] getAsIntArray() {
        return this.array;
    }

    @Override
    public TagType type() {
        return TagType.INT_ARRAY;
    }

    @Override
    void write(DataOutput output) throws IOException {
        output.writeInt(array.length);
        for (int item : array) {
            output.writeInt(item);
        }
    }

    @Override
    void read(DataInput input) throws IOException {
        int size = input.readInt();
        array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = input.readInt();
    }
}
