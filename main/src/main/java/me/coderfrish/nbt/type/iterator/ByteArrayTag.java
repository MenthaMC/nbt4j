package me.coderfrish.nbt.type.iterator;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.ElementTag;
import me.coderfrish.nbt.utils.iterator.ByteArrayIterator;

import java.util.Iterator;

public class ByteArrayTag extends ElementTag implements Iterable<Byte> {
    private final byte[] array;

    public ByteArrayTag(byte[] array) {
        this.array = array;
    }

    public ByteArrayTag(int size) {
        this(new byte[size]);
    }

    public byte get(int i) {
        return array[i];
    }

    public ByteArrayTag set(int i, byte v) {
        array[i] = v;
        return this;
    }

    public int size() {
        return array.length;
    }

    @Override
    public TagType type() {
        return TagType.BYTE_ARRAY;
    }

    @Override
    public Iterator<Byte> iterator() {
        return new ByteArrayIterator(array);
    }
}
