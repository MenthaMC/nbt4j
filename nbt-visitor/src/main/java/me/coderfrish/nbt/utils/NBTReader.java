package me.coderfrish.nbt.utils;

import me.coderfrish.nbt.api.TagEntry;
import me.coderfrish.nbt.type.ElementTag;
import me.coderfrish.nbt.type.iterator.*;
import me.coderfrish.nbt.type.primitive.*;
import me.coderfrish.nbt.visitor.NBTVisitor;

import java.io.IOException;
import java.io.InputStream;

public class NBTReader {
    private final CompoundTag root;

    public NBTReader(InputStream root) throws IOException {
        this.root = StreamUtils.fromStream(root);
    }

    public void accept(NBTVisitor visitor) {
        for (TagEntry entry : root) {
            ElementTag value = entry.getValue();
            String key = entry.getKey();
            visitor.visitElement(key, value);

            if (entry instanceof StringTag) {
                visitor.visitString(key, value.getAsString());
            } else if (entry instanceof IntTag) {
                visitor.visitInt(key, value.getAsInt());
            } else if (entry instanceof FloatTag) {
                visitor.visitFloat(key, value.getAsFloat());
            } else if (entry instanceof DoubleTag) {
                visitor.visitDouble(key, value.getAsDouble());
            } else if (entry instanceof ShortTag) {
                visitor.visitShort(key, value.getAsShort());
            } else if (entry instanceof LongTag) {
                visitor.visitLong(key, value.getAsLong());
            } else if (entry instanceof ByteTag) {
                visitor.visitByte(key, value.getAsByte());
            } else if (entry instanceof ByteArrayTag) {
                visitor.visitByteArray(key, value.getAsByteArray());
            } else if (entry instanceof IntArrayTag) {
                visitor.visitIntArray(key, value.getAsIntArray());
            } else if (entry instanceof LongArrayTag) {
                visitor.visitLongArray(key, value.getAsLongArray());
            } else if (entry instanceof ListTag) {
                visitor.visitList(key, value.getAsList());
            } else if (entry instanceof CompoundTag) {
                visitor.visitCompound(key, value.getAsCompound());
            }
        }
    }
}
