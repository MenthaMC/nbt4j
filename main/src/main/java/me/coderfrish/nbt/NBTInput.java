package me.coderfrish.nbt;

import me.coderfrish.nbt.common.TagType;
import me.coderfrish.nbt.io.input.DataInputStream;
import me.coderfrish.nbt.type.NBTCompound;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class NBTInput implements Closeable {
    private final DataInputStream input;

    public NBTInput(InputStream input) {
        this.input = new DataInputStream(input);
    }

    public NBTCompound read() throws IOException {
        input.readType();
        input.readUTF();
        return input.readCompound();
    }

    @Override
    public void close() throws IOException {
        input.close();
    }
}
