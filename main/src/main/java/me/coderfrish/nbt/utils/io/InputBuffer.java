package me.coderfrish.nbt.utils.io;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.ElementTag;
import me.coderfrish.nbt.type.iterator.*;
import me.coderfrish.nbt.type.primitive.*;
import me.coderfrish.nbt.utils.DecoderUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputBuffer extends DataInputStream {
    public InputBuffer(InputStream in) {
        super(in);
    }

    public ElementTag readTagCompound() throws IOException {
        CompoundTag compound = new CompoundTag();
        for (;;) {
            TagType type = readType();
            if (type == TagType.END) break;

            String key = readUTF();
            compound.put(key, DecoderUtils.decode(type, this));
        }

        return compound;
    }

    public ElementTag readTagList() throws IOException {
        ListTag list = new ListTag();
        TagType type = readType();
        if (type == TagType.END) {
            return new ListTag();
        }

        int length = readInt();
        for (int i = 0; i < length; i++) {
            list.add(DecoderUtils.decode(type, this));
        }

        return list;
    }

    public ElementTag readTagIntArray() throws IOException {
        int size = this.readInt();
        if (size < 0)
            throw new RuntimeException("IntArray tag length must over or equals 0.");

        int[] buffer = new int[size];

        for (int i = 0; i < size; i++)
            buffer[i] = this.readInt();

        return new IntArrayTag(buffer);
    }

    public ElementTag readTagLongArray() throws IOException {
        int size = this.readInt();
        if (size < 0)
            throw new RuntimeException("LongArray tag length must over or equals 0.");

        long[] buffer = new long[size];

        for (int i = 0; i < size; i++)
            buffer[i] = this.readLong();

        return new LongArrayTag(buffer);
    }

    public ElementTag readTagByteArray() throws IOException {
        int size = this.readInt();
        if (size < 0)
            throw new RuntimeException("ByteArray tag length must over or equals 0.");

        byte[] buffer = new byte[size];

        for (int i = 0; i < size; i++)
            buffer[i] = this.readByte();

        return new ByteArrayTag(buffer);
    }

    public TagType readType() throws IOException {
        return TagType.values()[readByte()];
    }

    public ElementTag readTagUTF() throws IOException {
        return new StringTag(this.readUTF());
    }

    public ElementTag readTagInt() throws IOException {
        return new IntTag(this.readInt());
    }

    public ElementTag readTagFloat() throws IOException {
        return new FloatTag(this.readFloat());
    }

    public ElementTag readTagLong() throws IOException {
        return new LongTag(this.readLong());
    }

    public ElementTag readTagDouble() throws IOException {
        return new DoubleTag(this.readDouble());
    }

    public ElementTag readTagByte() throws IOException {
        return new ByteTag(this.readByte());
    }

    public ElementTag readTagShort() throws IOException {
        return new ShortTag(this.readShort());
    }
}
