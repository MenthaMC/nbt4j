package me.coderfrish.util;

import me.coderfrish.TagType;
import me.coderfrish.tag.*;
import me.coderfrish.tag.array.ByteArrayTag;
import me.coderfrish.tag.array.IntArrayTag;
import me.coderfrish.tag.array.LongArrayTag;
import me.coderfrish.core.NBTException;
import me.coderfrish.ITag;
import me.coderfrish.tag.compound.CompoundTag;
import me.coderfrish.tag.list.ListTag;

import java.io.DataOutput;
import java.io.IOException;

public class WriteUtil {
    public static void writeString(String name, DataOutput out) throws IOException {
        out.writeUTF(name);
    }

    public static void writeType(TagType type, DataOutput out) throws IOException {
        out.write(type.ordinal());
    }

    public static void writeCompound(CompoundTag compound, DataOutput output) throws Exception {
        for (CompoundTag.Entry entry : compound) {
            ITag tag = entry.value();

            output.write(tag.type().ordinal());
            output.writeUTF(entry.key());
            writeValue(tag, output);
        }

        writeType(TagType.END, output);
    }

    private static <V extends ITag> void writeList(ListTag list, DataOutput output) throws Exception {
        writeType(list.getFirst().type(), output);
        output.writeInt(list.size());
        for (ITag tag : list) {
            writeValue(tag, output);
        }
    }

    private static void writeValue(ITag tag, DataOutput output) throws Exception {
        switch (tag) {
            case CompoundTag compound -> writeCompound(compound, output);
            case ListTag list -> {
                if (list.isEmpty()) {
                    writeType(TagType.END, output);
                    output.writeInt(0);
                    return;
                }

                writeList(list, output);
            }
            default -> {
                switch (tag) {
                    case ByteTag byteTag -> output.writeByte(byteTag.value());
                    case ShortTag shortTag -> output.writeShort(shortTag.value());
                    case IntTag intTag -> output.writeInt(intTag.value());
                    case FloatTag floatTag -> output.writeFloat(floatTag.value());
                    case DoubleTag doubleTag -> output.writeDouble(doubleTag.value());
                    case StringTag stringTag -> output.writeUTF(stringTag.value());
                    case ByteArrayTag byteArray -> {
                        output.writeInt(byteArray.size());
                        for (Byte item : byteArray) {
                            output.writeByte(item);
                        }
                    }

                    case IntArrayTag intArray -> {
                        output.writeInt(intArray.size());
                        for (Integer item : intArray) {
                            output.writeInt(item);
                        }
                    }

                    case LongArrayTag longArray -> {
                        output.writeInt(longArray.size());
                        for (Long item : longArray) {
                            output.writeLong(item);
                        }
                    }
                    default -> throw new NBTException("Unknown tag: " + tag);
                }
            }
        }
    }
}
