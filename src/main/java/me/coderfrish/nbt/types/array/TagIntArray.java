package me.coderfrish.nbt.types.array;

import me.coderfrish.nbt.ITagBase;
import me.coderfrish.nbt.types.TagInt;
import me.coderfrish.nbt.TagType;

import java.io.DataOutputStream;
import java.util.Arrays;

@SuppressWarnings("all")
public record TagIntArray(int[] array) implements ITagBase {
    @Override
    public void write(DataOutputStream out) throws Exception {
        if (array.length == 0) {
            out.writeInt(0);
            return;
        }

        out.writeInt(array.length);
        for (int ints : array) {
            new TagInt(ints).write(out);
        }
    }

    @Override
    public TagType getType() {
        return TagType.INT_ARRAY;
    }

    @Override
    public String toString() {
        return "TagIntArray{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
