package xyz.frish2021.nbt.util;

import xyz.frish2021.nbt.array.Array;
import xyz.frish2021.nbt.array.ByteArrayTag;
import xyz.frish2021.nbt.array.IntArrayTag;
import xyz.frish2021.nbt.array.LongArrayTag;
import xyz.frish2021.nbt.compound.Compound;
import xyz.frish2021.nbt.exception.NBTWriteException;
import xyz.frish2021.nbt.list.List;
import xyz.frish2021.nbt.primitive.Primitive;
import xyz.frish2021.nbt.primitive.StringTag;
import xyz.frish2021.nbt.primitive.number.*;
import xyz.frish2021.nbt.tag.ITag;
import xyz.frish2021.nbt.tag.TagType;

import java.io.DataOutput;
import java.io.IOException;

public class WriteUtil {
    public static void writeString(String name, DataOutput out) throws IOException {
        out.writeUTF(name);
    }

    public static void writeType(TagType type, DataOutput out) throws IOException {
        out.write(type.ordinal());
    }

    public static void writeCompound(Compound compound, DataOutput output) throws Exception {
        for (Compound.Entry entry : compound) {
            ITag tag = entry.value();

            output.write(tag.type().ordinal());
            output.writeUTF(entry.key());
            writeValue(tag, output);
        }

        writeType(TagType.END, output);
    }

    private static <V extends ITag> void writeList(List<V> list, DataOutput output) throws Exception {
        writeType(list.getFirst().type(), output);
        output.writeInt(list.size());
        for (V tag : list) {
            writeValue(tag, output);
        }
    }

    private static void writeValue(ITag tag, DataOutput output) throws Exception {
        if (tag instanceof Compound compound) {
            writeCompound(compound, output);
        } else if (tag instanceof Primitive<?>) {
            switch (tag) {
                case ByteTag byteTag -> output.writeByte(byteTag.value());
                case ShortTag shortTag -> output.writeShort(shortTag.value());
                case IntTag intTag -> output.writeInt(intTag.value());
                case FloatTag floatTag -> output.writeFloat(floatTag.value());
                case DoubleTag doubleTag -> output.writeDouble(doubleTag.value());
                case StringTag stringTag -> output.writeUTF(stringTag.value());
                default -> throw new NBTWriteException("Unknown tag: " + tag);
            }
        } else if (tag instanceof Array<?>) {
            switch (tag) {
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

                default -> throw new NBTWriteException("Unknown tag: " + tag);
            }
        } else if (tag instanceof List<?> list) {
            if (list.isEmpty()) {
                writeType(TagType.END, output);
                output.writeInt(0);
                return;
            }

            writeList(list, output);
        }
    }
}
