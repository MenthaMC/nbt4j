package me.coderfrish.nbt.io.input;

import me.coderfrish.nbt.common.TagType;
import me.coderfrish.nbt.type.*;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class DataInputStream extends BufferedInputStream implements Closeable {
    private static final int DEFAULT_BUFFER_SIZE = 8192;

    private final byte[] readLongBuffer = new byte[8];
    private final byte[] readIntBuffer = new byte[4];
    private final byte[] readShortBuffer = new byte[2];

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
        readFully(readShortBuffer, 0, 2);
        return (short) ((readShortBuffer[0] << 8) + (readShortBuffer[1]));
    }

    public int readInt() throws IOException {
        readFully(readIntBuffer, 0, 4);
        return ((readIntBuffer[0] << 24) + (readIntBuffer[1] << 16) + (readIntBuffer[2] << 8) + (readIntBuffer[3]));
    }

    public long readLong() throws IOException {
        readFully(readLongBuffer, 0, 8);
        return (((long)readLongBuffer[0] << 56) +
                ((long)(readLongBuffer[1] & 255) << 48) +
                ((long)(readLongBuffer[2] & 255) << 40) +
                ((long)(readLongBuffer[3] & 255) << 32) +
                ((long)(readLongBuffer[4] & 255) << 24) +
                ((readLongBuffer[5] & 255) << 16) +
                ((readLongBuffer[6] & 255) <<  8) +
                ((readLongBuffer[7] & 255)));
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
}
