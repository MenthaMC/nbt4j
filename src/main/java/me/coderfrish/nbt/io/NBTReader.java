package me.coderfrish.nbt.io;

import me.coderfrish.nbt.CompoundTag;
import me.coderfrish.nbt.ITagBase;
import me.coderfrish.nbt.NBTUtil;
import me.coderfrish.nbt.snbt.SNBTReader;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("all")
public class NBTReader {
    public NBTReader() {
        throw new UnsupportedOperationException();
    }

    public static CompoundTag readNBT(InputStream input) {
        try(DataInputStream buffer = new DataInputStream(input)) {
            ITagBase.TagType type = ITagBase.TagType.getTypeById(buffer.readByte());
            if (type != CompoundTag.TagType.COMPOUND) return new CompoundTag();

            return (CompoundTag) NBTUtil.readValue(buffer, type, false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static CompoundTag readNBT(File file) {
        try(InputStream buffer = new FileInputStream(file)) {
            return readNBT(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static CompoundTag readSNBT(File file, Charset charset) {
        try {
            return readSNBT(new FileInputStream(file), charset);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static CompoundTag readSNBT(InputStream file, Charset charset) {
        try {
            return readSNBT(new String(file.readAllBytes(), charset));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static CompoundTag readSNBT(File file) {
        return readSNBT(file, StandardCharsets.UTF_8);
    }

    public static CompoundTag readSNBT(String snbt) {
        return new SNBTReader(snbt).parserSNBT();
    }
}
