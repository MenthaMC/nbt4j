package me.coderfrish.nbt.utils.io;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.ElementTag;
import me.coderfrish.nbt.type.iterator.ByteArrayTag;
import me.coderfrish.nbt.type.iterator.IntArrayTag;
import me.coderfrish.nbt.type.iterator.ListTag;
import me.coderfrish.nbt.type.iterator.LongArrayTag;
import me.coderfrish.nbt.utils.EncoderUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class OutputBuffer extends DataOutputStream {
    public OutputBuffer(OutputStream out) {
        super(out);
    }

    @Override
    public void write(int b) throws IOException {
        super.write(b);
        this.flush();
    }

    public void writeTagCompound(ElementTag value) throws IOException {
        for (Map.Entry<String, ElementTag> entry : value.getAsCompound().entrySet()) {
            TagType type = entry.getValue().type();

            this.writeType(type);
            this.writeUTF(entry.getKey());
            EncoderUtils.encode(type, this, entry.getValue());
        }

        writeType(TagType.END);
    }

    public void writeTagList(ElementTag element) throws IOException {
        ListTag value = element.getAsList();
        if (value.isEmpty()) {
            this.writeType(TagType.END);
            this.writeInt(0);
            return;
        }

        TagType type = value.getFirst().type();
        this.writeType(type);
        this.writeInt(value.size());
        for (ElementTag tag : value) {
            EncoderUtils.encode(type, this, tag);
        }
    }

    public void writeTagIntArray(ElementTag tag) throws IOException {
        IntArrayTag value = tag.getAsIntArray();
        this.writeInt(value.size());
        for (int item : value) {
            this.writeInt(item);
        }
    }

    public void writeTagLongArray(ElementTag tag) throws IOException {
        LongArrayTag value = tag.getAsLongArray();
        this.writeInt(value.size());
        for (long item : value) {
            this.writeLong(item);
        }
    }

    public void writeTagByteArray(ElementTag tag) throws IOException {
        ByteArrayTag value = tag.getAsByteArray();
        this.writeInt(value.size());
        for (byte item : value) {
            this.writeByte(item);
        }
    }

    public void writeType(TagType type) throws IOException {
        this.write(type.ordinal());
    }

    public void writeTagByte(ElementTag value) throws IOException {
        this.writeByte(value.getAsByte());
    }

    public void writeTagDouble(ElementTag value) throws IOException {
        this.writeDouble(value.getAsDouble());
    }

    public void writeTagFloat(ElementTag value) throws IOException {
        this.writeFloat(value.getAsFloat());
    }

    public void writeTagShort(ElementTag value) throws IOException {
        this.writeShort(value.getAsShort());
    }

    public void writeTagLong(ElementTag value) throws IOException {
        this.writeLong(value.getAsLong());
    }

    public void writeTagInt(ElementTag value) throws IOException {
        this.writeInt(value.getAsInt());
    }

    public void writeTagUTF(ElementTag value) throws IOException {
        this.writeUTF(value.getAsString());
    }
}
