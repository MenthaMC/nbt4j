package me.coderfrish.nbt.types.array;

import me.coderfrish.nbt.ITagBase;
import me.coderfrish.nbt.types.TagLong;
import me.coderfrish.nbt.TagType;

import java.io.DataOutputStream;
import java.util.Arrays;

@SuppressWarnings("all")
public record TagLongArray(long[] array) implements ITagBase {
    @Override
    public void write(DataOutputStream out) throws Exception {
        if (array.length == 0) {
            out.writeInt(0);
            return;
        }

        out.writeInt(array.length);
        for (long longs : array) {
            new TagLong(longs).write(out);
        }
    }

    @Override
    public TagType getType() {
        return TagType.LONG_ARRAY;
    }

    @Override
    public String toString() {
        return "TagLongArray{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
