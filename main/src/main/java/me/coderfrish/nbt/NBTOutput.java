package me.coderfrish.nbt;

import me.coderfrish.nbt.common.TagType;
import me.coderfrish.nbt.io.output.DataOutputStream;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

public class NBTOutput implements Closeable {
    private final DataOutputStream output;

    public NBTOutput(OutputStream output) {
        this.output = new DataOutputStream(output);
    }

    public void write(String name, Object object) throws IOException {
        output.writeType(TagType.COMPOUND);
        output.writeUTF(name);
        output.writeObject(object);
    }

    public void write(Object object) throws IOException {
        this.write("", object);
    }

    @Override
    public void close() throws IOException {
        output.close();
    }
}
