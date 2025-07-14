package me.coderfrish.nbt.utils.io;

import me.coderfrish.nbt.api.TagType;
import me.coderfrish.nbt.type.ElementTag;
import me.coderfrish.nbt.type.iterator.CompoundTag;
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
            ElementTag value = DecoderUtils.getDecoders().get(type).decode(this);
            compound.put(key, value);
        }

        return compound;
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
