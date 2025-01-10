package me.coderfrish.nbt.type;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class NBTByteArray implements Iterable<Byte> {
    private final byte[] array;

    public NBTByteArray(byte[] array) {
        this.array = array;
    }

    public NBTByteArray(int size) {
        array = new byte[size];
    }

    public byte get(int index) {
        return array[index];
    }

    public NBTByteArray set(int index, byte value) {
        array[index] = value;
        return this;
    }

    public int size() {
        return array.length;
    }

    public Stream<Byte> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    public Iterator<Byte> iterator() {
        return new ByteArrayIterator(array);
    }

    private static class ByteArrayIterator implements Iterator<Byte> {
        private final byte[] array;
        private int index = 0;

        private ByteArrayIterator(byte[] array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return index < array.length;
        }

        @Override
        public Byte next() {
            return array[index++];
        }
    }
}
