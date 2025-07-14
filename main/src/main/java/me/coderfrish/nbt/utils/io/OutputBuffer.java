package me.coderfrish.nbt.utils.io;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.ElementTag;
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
            EncoderUtils.getEncoders().get(type).encode(this, entry.getValue());
        }

        writeType(TagType.END);
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
