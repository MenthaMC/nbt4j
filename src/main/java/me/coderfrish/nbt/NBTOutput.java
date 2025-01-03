package me.coderfrish.nbt;

import me.coderfrish.nbt.type.TagObject;

import static me.coderfrish.nbt.Utility.*;

import java.io.*;

public class NBTOutput implements Closeable {
    private final RandomAccessFile raf;

    public NBTOutput(RandomAccessFile raf) {
        this.raf = raf;
    }

    public NBTOutput(File file) throws FileNotFoundException {
        this(new RandomAccessFile(file, "rw"));
    }

    public void write(String name, TagObject tag) {
        try {
            writeType(raf, TagType.COMPOUND);
            writeString(raf, name);
            writeObject(raf, tag);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        raf.close();
    }
}
