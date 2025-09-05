package me.coderfrish.nbt.visitor;

import me.coderfrish.nbt.type.ElementTag;
import me.coderfrish.nbt.type.iterator.*;
import me.coderfrish.nbt.type.primitive.*;

public abstract class NBTVisitor {
    public void visitElement(String key, ElementTag value) {
    }

    public void visitInt(String key, int value) {
    }

    public void visitString(String key, String value) {
    }

    public void visitFloat(String key, float value) {
    }

    public void visitShort(String key, short value) {
    }

    public void visitLong(String key, long value) {
    }

    public void visitDouble(String key, double value) {
    }

    public void visitByte(String key, byte value) {
    }

    public void visitByteArray(String key, ByteArrayTag value) {
    }

    public void visitIntArray(String key, IntArrayTag value) {
    }

    public void visitLongArray(String key, LongArrayTag value) {
    }

    public void visitList(String key, ListTag value) {
    }

    public void visitCompound(String key, CompoundTag value) {
    }
}
