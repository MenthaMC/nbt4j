package me.coderfrish.nbt.io.input;

import me.coderfrish.nbt.common.TagType;
import me.coderfrish.nbt.type.*;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class DataInputStream extends BufferedInputStream implements Closeable {
    private static final VarHandle SHORT = create(short[].class);
    private static final VarHandle INT = create(int[].class);
    private static final VarHandle LONG = create(long[].class);

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    private final byte[] readBuffer = new byte[8];

    public DataInputStream(InputStream in) {
        super(in, DEFAULT_BUFFER_SIZE);
    }

    public NBTCompound readCompound() throws IOException {
        NBTCompound compound = new NBTCompound();
        for (;;) {
            TagType type = readType();
            if (type == TagType.END) break;

            String key = readUTF();
            Object value = type.decoder.decode(this);
            compound.put(key, value);
        }

        return compound;
    }

    public NBTIntArray readIntArray() throws IOException {
        int length = readInt();
        if (length < 0) {
            throw new RuntimeException("The prefix is of negative length ):");
        }

        byte[] bytes = new byte[length * 4];
        readFully(bytes);
        int byteIndex = 0;

        int[] intArray = new int[length];
        for (int i = 0; i < length; i++, byteIndex += 4) {
            int element = 0;
            int bitOffset = 24;
            for (int b = 0; b < 4; b++, bitOffset -= 8) {
                element |= (bytes[byteIndex + b] & 0xFF) << bitOffset;
            }
            intArray[i] = element;
        }

        return new NBTIntArray(intArray);
    }

    public NBTByteArray readByteArray() throws IOException {
        int length = readInt();
        if (length < 0) {
            throw new RuntimeException("The prefix is of negative length ):");
        }

        byte[] bytes = new byte[length];
        readFully(bytes);

        return new NBTByteArray(bytes);
    }

    public NBTList readList() throws IOException {
        NBTList list = new NBTList();
        TagType type = readType();
        if (type == TagType.END) {
            return new NBTList();
        }

        int length = readInt();
        for (int i = 0; i < length; i++) {
            list.add(type.decoder.decode(this));
        }

        return list;
    }

    public NBTLongArray readLongArray() throws IOException {
        int length = readInt();
        if (length < 0) {
            throw new RuntimeException("The prefix is of negative length ):");
        } else if (length == 0) {
            return new NBTLongArray(new long[0]);
        }

        byte[] bytes = new byte[length * 8];
        readFully(bytes);
        int byteIndex = 0;

        long[] longArray = new long[length];
        for (int i = 0; i < length; i++, byteIndex += 8) {
            long element = 0;
            int bitOffset = 56;
            for (int b = 0; b < 8; b++, bitOffset -= 8) {
                element |= ((long) (bytes[byteIndex + b] & 0xFF)) << bitOffset;
            }
            longArray[i] = element;
        }
        return new NBTLongArray(longArray);
    }

    public TagType readType() throws IOException {
        return TagType.values()[readByte()];
    }

    public void readFully(byte[] b) throws IOException {
        readFully(b, 0, b.length);
    }

    public void readFully(byte[] b, int off, int len) throws IOException {
        int result = read(b, off, len);
        if (result == -1)
            throw new EOFException();
    }

    public boolean readBoolean() throws IOException {
        return readByte() == 1;
    }

    public byte readByte() throws IOException {
        byte result = (byte) read();
        if (result == -1)
            throw new EOFException();

        return result;
    }

    public short readShort() throws IOException {
        readFully(readBuffer, 0, 2);
        return (short) SHORT.get(readBuffer, 0);
    }

    public int readInt() throws IOException {
        readFully(readBuffer, 0, 4);
        return (int) INT.get(readBuffer, 0);
    }

    public long readLong() throws IOException {
        readFully(readBuffer, 0, 8);
        return (long) LONG.get(readBuffer, 0);
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    public String readUTF() throws IOException {
        byte[] strBytes = new byte[readShort()];
        readFully(strBytes);

        return new String(strBytes, StandardCharsets.UTF_8);
    }

    private static VarHandle create(Class<?> viewArrayClass) {
        return MethodHandles.byteArrayViewVarHandle(viewArrayClass, ByteOrder.BIG_ENDIAN);
    }
}
