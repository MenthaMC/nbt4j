package me.coderfrish.nbt.io;

import me.coderfrish.nbt.CompoundTag;

import java.io.*;

@SuppressWarnings("all")
public class NBTWriter {
    public NBTWriter() {
        throw new UnsupportedOperationException();
    }

    public static void writeNBT(CompoundTag compoundTag, OutputStream output) {
        try(DataOutputStream buffer = new DataOutputStream(output)) {
            buffer.write(compoundTag.type().ordinal());
            buffer.writeUTF(compoundTag.key());
            compoundTag.write(buffer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeNBT(CompoundTag compoundTag, File file) {
        try {
            writeNBT(compoundTag, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
