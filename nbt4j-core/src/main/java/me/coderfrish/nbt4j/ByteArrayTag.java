package me.coderfrish.nbt4j;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ByteArrayTag extends ElementTag {
    private byte[] array;

    public ByteArrayTag(byte[] array) {
        this.array = array;
    }

    @Override
    public byte[] getAsByteArray() {
        return this.array;
    }

    @Override
    public TagType type() {
        return TagType.BYTE_ARRAY;
    }

    @Override
    void write(DataOutput output) throws IOException {
        output.writeInt(array.length);
        for (byte item : array) {
            output.writeByte(item);
        }
    }

    @Override
    void read(DataInput input) throws IOException {
        int size = input.readInt();
        array = new byte[size];
        for (int i = 0; i < size; i++)
            array[i] = input.readByte();
    }
}
