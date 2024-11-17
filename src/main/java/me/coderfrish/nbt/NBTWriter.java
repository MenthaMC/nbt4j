package me.coderfrish.nbt;

import me.coderfrish.nbt.types.TagCompound;

import java.io.*;

import static me.coderfrish.nbt.util.CommonUtil.*;

@SuppressWarnings("all")
public class NBTWriter {
    public static void writeNBTFile(String name, TagCompound tag, OutputStream output) {
        try(DataOutputStream out = new DataOutputStream(output)) {
            writeType(out, TagType.COMPOUND);
            writeString(out, name);
            tag.write(out);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeNBTFile(TagCompound tag, OutputStream output) {
        writeNBTFile("", tag, output);
    }

    public static void writeNBTFile(String name, TagCompound tag, File file) {
        try {
            writeNBTFile(name, tag, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeNBTFile(TagCompound tag, File file) {
        writeNBTFile("", tag, file);
    }

    public NBTWriter() {
        throw new UnsupportedOperationException("This class can be used directly without initialization.");
    }
}
