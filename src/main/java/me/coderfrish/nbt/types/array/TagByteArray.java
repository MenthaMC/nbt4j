package me.coderfrish.nbt.types.array;

import me.coderfrish.nbt.ITagBase;
import me.coderfrish.nbt.TagType;
import me.coderfrish.nbt.types.*;

import java.io.DataOutputStream;
import java.util.Arrays;

@SuppressWarnings("all")
public record TagByteArray(byte[] array) implements ITagBase {
    @Override
    public void write(DataOutputStream out) throws Exception {
        if (array.length == 0) {
            out.writeInt(0);
            return;
        }

        out.writeInt(array.length);
        for (byte bytes : array) {
            new TagByte(bytes).write(out);
        }
    }

    @Override
    public TagType getType() {
        return TagType.BYTE_ARRAY;
    }

    @Override
    public String toString() {
        return "TagByteArray{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
