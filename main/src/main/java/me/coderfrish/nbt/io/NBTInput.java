package me.coderfrish.nbt.io;

import me.coderfrish.nbt.type.TagObject;

import java.io.*;

import static me.coderfrish.nbt.common.Utility.*;

public class NBTInput implements Closeable {
    private final RandomAccessFile raf;

    public NBTInput(RandomAccessFile raf) {
        this.raf = raf;
    }

    public NBTInput(File file) throws FileNotFoundException {
        this(new RandomAccessFile(file, "rw"));
    }

    public TagObject read() {
        try {
            readType(raf);
            readString(raf);
            return readObject(raf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        raf.close();
    }
}
