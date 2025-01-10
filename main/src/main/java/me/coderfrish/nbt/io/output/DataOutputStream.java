package me.coderfrish.nbt.io.output;

import me.coderfrish.nbt.common.TagType;
import me.coderfrish.nbt.common.Utility;
import me.coderfrish.nbt.type.*;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class DataOutputStream extends BufferedOutputStream implements Closeable {
    private static final int DEFAULT_MAX_BUFFER_SIZE = 8192;

    public DataOutputStream(OutputStream out) {
        super(out, DEFAULT_MAX_BUFFER_SIZE);
    }

    public void writeObject(Object obj) throws IOException {
        if (obj instanceof NBTCompound) {
            this.writeCompound((NBTCompound) obj);
        } else if (obj instanceof Map<?, ?>) {
            NBTCompound compound = new NBTCompound();

            for (Map.Entry<?, ?> entry : ((Map<?, ?>) obj).entrySet()) {
                compound.put(entry.getKey().toString(), entry.getValue());
            }

            this.writeCompound(compound);
        }
    }

    public void writeCompound(NBTCompound compound) throws IOException {
        for (Map.Entry<String, NBTPrimitive> entry : compound) {
            TagType type = Utility.type(entry.getValue());

            this.writeType(type);
            this.writeUTF(entry.getKey());
            type.encoder.encode(this, entry.getValue());
        }

        writeType(TagType.END);
    }

    public void writeType(TagType type) throws IOException {
        this.write(type.ordinal());
    }

    @Override
    public void write(int b) throws IOException {
        super.write(b);
        this.flush();
    }

    public void writeBytes(byte[] value) throws IOException {
        this.write(value);
    }

    public void writeIntArray(NBTIntArray value) throws IOException {
        this.writeInt(value.size());
        for (int item : value) {
            this.writeInt(item);
        }
    }

    public void writeByteArray(NBTByteArray value) throws IOException {
        this.writeInt(value.size());
        for (byte item : value) {
            this.writeByte(item);
        }
    }

    public void writeLongArray(NBTLongArray value) throws IOException {
        this.writeInt(value.size());
        for (long item : value) {
            this.writeLong(item);
        }
    }

    public void writeList(NBTList list) throws IOException {
        if (list.isEmpty()) {
            this.writeType(TagType.END);
            this.writeInt(0);
            return;
        }

        TagType types = Utility.type(list.getFirst());
        this.writeType(types);
        this.writeInt(list.size());
        for (NBTPrimitive tag : list) {
            types.encoder.encode(this, tag);
        }
    }

    public void writeBoolean(boolean v) throws IOException {
        this.write(v ? 1 : 0);
    }

    public void writeByte(int v) throws IOException {
        this.write(v);
    }

    public void writeShort(int v) throws IOException {
        this.write((v >>> 8) & 0xFF);
        this.write(v & 0xFF);
    }

    public void writeInt(int v) throws IOException {
        this.write((v >>> 24) & 0xFF);
        this.write((v >>> 16) & 0xFF);
        this.write((v >>> 8) & 0xFF);
        this.write(v & 0xFF);
    }

    public void writeLong(long v) throws IOException {
        this.write((int) (v >>> 56) & 0xFF);
        this.write((int) (v >>> 48) & 0xFF);
        this.write((int) (v >>> 40) & 0xFF);
        this.write((int) (v >>> 32) & 0xFF);
        this.write((int) (v >>> 24) & 0xFF);
        this.write((int) (v >>> 16) & 0xFF);
        this.write((int) (v >>> 8) & 0xFF);
        this.write((int) (v) & 0xFF);
    }

    public void writeFloat(float v) throws IOException {
        this.writeInt(Float.floatToIntBits(v));
    }

    public void writeDouble(double v) throws IOException {
        this.writeLong(Double.doubleToLongBits(v));
    }

    @SuppressWarnings("all")
    public void writeUTF(String value) throws IOException {
        if (value == null) {
            this.writeInt(0);
            return;
        }

        byte[] strBytes = value.getBytes(StandardCharsets.UTF_8);
        this.writeShort(strBytes.length);
        this.writeBytes(strBytes);
    }
}
