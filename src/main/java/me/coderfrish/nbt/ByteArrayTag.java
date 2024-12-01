package me.coderfrish.nbt;

import java.io.DataOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("ALL")
public record ByteArrayTag(String key, byte[] value) implements ITagBase, Iterable<Byte> {
    public ByteArrayTag(byte[] array) {
        this("", array);
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public byte[] value() {
        return value;
    }

    @Override
    public void write(DataOutput output) throws Exception {
        if (value == null) {
            output.writeInt(0);
            return;
        }

        output.writeInt(value.length);
        for (Byte item : value) {
            output.writeByte(item);
        }
    }

    @Override
    public TagType type() {
        return TagType.BYTE_ARRAY;
    }

    @Override
    public Iterator<Byte> iterator() {
        List<Byte> list = new ArrayList<>();
        for (byte item : value) {
            list.add(item);
        }

        return list.iterator();
    }

    public byte get(int index) {
        return value[index];
    }

    @Override
    public void forEach(Consumer<? super Byte> action) {
        for (byte value : value) {
            action.accept(value);
        }
    }

    @Override
    public String toString() {
        return "bytes" + Arrays.toString(value);
    }
}
