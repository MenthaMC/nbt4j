package me.coderfrish.nbt.util;

import me.coderfrish.nbt.ITagBase;
import me.coderfrish.nbt.exception.NBTException;
import me.coderfrish.nbt.TagType;
import me.coderfrish.nbt.types.*;
import me.coderfrish.nbt.types.array.TagByteArray;
import me.coderfrish.nbt.types.array.TagIntArray;
import me.coderfrish.nbt.types.array.TagList;
import me.coderfrish.nbt.types.array.TagLongArray;

import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static me.coderfrish.nbt.util.CommonUtil.readString;
import static me.coderfrish.nbt.util.CommonUtil.readType;

@SuppressWarnings("all")
public class ReaderUtil {
    public static ITagBase readValue(DataInputStream inp, TagType type) {
        try {
            return switch (type) {
                case BYTE -> new TagByte(inp.readByte());
                case SHORT -> new TagShort(inp.readShort());
                case INT -> new TagInt(inp.readInt());
                case LONG -> new TagLong(inp.readLong());
                case FLOAT -> new TagFloat(inp.readFloat());
                case DOUBLE -> new TagDouble(inp.readDouble());
                case BYTE_ARRAY -> readByteArray(inp);
                case STRING -> new TagString(readString(inp));
                case LIST -> readList(inp);
                case COMPOUND -> readCompound(inp);
                case INT_ARRAY -> readIntArray(inp);
                case LONG_ARRAY -> readLongArray(inp);

                default -> throw new NBTException("Unexpected value: " + type);
            };
        } catch (Exception e) {
            throw new NBTException(e);
        }
    }

    public static TagCompound readCompound(DataInputStream inp) {
        Map<String, ITagBase> compounds = new LinkedHashMap<>();
        for (;;) {
            TagType type = readType(inp);
            if (type == TagType.END) break;

            String s = readString(inp);
            ITagBase value = readValue(inp, type);
            compounds.put(s, value);
        }
        return new TagCompound(compounds);
    }

    public static ITagBase readList(DataInputStream inp) {
        ArrayList<ITagBase> list = new ArrayList<>();
        try {
            TagType type = readType(inp);
            if (type == TagType.END) {
                return new TagList<>(new ArrayList<>());
            }

            int length = inp.readInt();
            for (int i = 0; i < length; i++) {
                list.add(readValue(inp, type));
            }

            return new TagList<>(list);
        } catch (Exception e) {
            throw new NBTException(e);
        }
    }

    public static ITagBase readLongArray(DataInputStream inp) {
        try {
            int length = inp.readInt();
            if (length < 0) {
                throw new NBTException("TAG_Long_Array was prefixed with a negative length");
            } else if (length == 0) {
                return new TagLongArray(new long[0]);
            }

            byte[] bytes = new byte[length * 8];
            inp.readFully(bytes);
            int byteIndex = 0;

            long[] longArray = new long[length];
            for (int i = 0; i < length; i++, byteIndex += 8) {
                long element = 0;
                int bitOffset = 56;
                for (int b = 0; b < 8; b++, bitOffset -= 8) {
                    element |= ((long) (bytes[byteIndex + b] & 0xFF)) << bitOffset;
                }
                longArray[i] = element;
            }
            return new TagLongArray(longArray);
        } catch (Exception e) {
            throw new NBTException(e);
        }
    }

    public static ITagBase readIntArray(DataInputStream inp) {
        try {
            int length = inp.readInt();
            if (length < 0) {
                throw new NBTException("TAG_Int_Array was prefixed with a negative length");
            }

            byte[] bytes = new byte[length * 4];
            inp.readFully(bytes);
            int byteIndex = 0;

            int[] intArray = new int[length];
            for (int i = 0; i < length; i++, byteIndex += 4) {
                int element = 0;
                int bitOffset = 24;
                for (int b = 0; b < 4; b++, bitOffset -= 8) {
                    element |= (bytes[byteIndex + b] & 0xFF) << bitOffset;
                }
                intArray[i] = element;
            }

            return new TagIntArray(intArray);
        } catch (Exception e) {
            throw new NBTException(e);
        }
    }

    public static ITagBase readByteArray(DataInputStream inp) {
        try {
            int length = inp.readInt();
            if (length < 0) {
                throw new NBTException("TAG_Byte_Array was prefixed with a negative length");
            }

            byte[] bytes = new byte[length];
            inp.readFully(bytes);

            return new TagByteArray(bytes);
        } catch (Exception e) {
            throw new NBTException(e);
        }
    }
}
