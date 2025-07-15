package me.coderfrish.nbt.utils;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.iterator.CompoundTag;
import me.coderfrish.nbt.utils.io.InputBuffer;
import me.coderfrish.nbt.utils.io.OutputBuffer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtils {
    public static void toStream(CompoundTag compound, OutputStream output) throws IOException {
        toStream("", compound, output);
    }

    public static void toStream(String name, CompoundTag compound, OutputStream output) throws IOException {
        try(OutputBuffer buffer = new OutputBuffer(output)) {
            buffer.writeType(TagType.COMPOUND);
            buffer.writeUTF(name);
            buffer.writeTagCompound(compound);
        }
    }

    public static CompoundTag fromStream(InputStream input) throws IOException {
        try(InputBuffer buffer = new InputBuffer(input)) {
            if (buffer.readType() != TagType.COMPOUND)
                throw new IllegalArgumentException("First tag must be compound tag.");

            buffer.readUTF();
            return buffer.readTagCompound().getAsCompound();
        }
    }
}
