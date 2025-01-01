package me.coderfrish.util;

import me.coderfrish.TagType;
import me.coderfrish.core.NBTException;
import me.coderfrish.tag.*;
import me.coderfrish.tag.array.ByteArrayTag;
import me.coderfrish.tag.array.IntArrayTag;
import me.coderfrish.tag.array.LongArrayTag;
import me.coderfrish.tag.list.ListTag;
import me.coderfrish.tag.compound.CompoundTag;
import me.coderfrish.ITag;

import java.io.DataInput;
import java.io.IOException;
import java.util.regex.Pattern;

public class ReaderUtil {
    private static final Pattern DOUBLE_PATTERN_NOSUFFIX = Pattern.compile("[-+]?(?:[0-9]+[.]|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?", Pattern.CASE_INSENSITIVE);
    private static final Pattern DOUBLE_PATTERN = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?d", Pattern.CASE_INSENSITIVE);
    private static final Pattern FLOAT_PATTERN = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?f", Pattern.CASE_INSENSITIVE);
    private static final Pattern BYTE_PATTERN = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)b", Pattern.CASE_INSENSITIVE);
    private static final Pattern LONG_PATTERN = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)l", Pattern.CASE_INSENSITIVE);
    private static final Pattern SHORT_PATTERN = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)s", Pattern.CASE_INSENSITIVE);
    private static final Pattern INT_PATTERN = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)");

    public static String readString(DataInput in) throws IOException {
        return in.readUTF();
    }

    public static TagType readType(DataInput in) throws IOException {
        return TagType.values()[in.readByte()];
    }

    public static CompoundTag readCompound(DataInput in) throws IOException {
        CompoundTag tag = new CompoundTag();
        for (;;) {
            TagType type = readType(in);
            if (type == TagType.END) break;

            String key = readString(in);
            ITag value = readValue(in, type);
            tag.put(key, value);
        }

        return tag;
    }

    private static ITag readValue(DataInput in, TagType type) throws IOException {
        return switch (type) {
            case BYTE -> new ByteTag(in.readByte());
            case LONG -> new LongTag(in.readLong());
            case FLOAT -> new FloatTag(in.readFloat());
            case DOUBLE -> new DoubleTag(in.readDouble());
            case STRING -> new StringTag(in.readUTF());
            case INT -> new IntTag(in.readInt());
            case SHORT -> new ShortTag(in.readShort());
            case COMPOUND -> readCompound(in);
            case INT_ARRAY -> {
                int length = in.readInt();
                if (length < 0) {
                    throw new NBTException("TAG_Int_Array was prefixed with a negative length");
                }

                byte[] bytes = new byte[length * 4];
                in.readFully(bytes);
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

                yield new IntArrayTag(intArray);
            }
            case BYTE_ARRAY -> {
                int length = in.readInt();
                if (length < 0) {
                    throw new NBTException("TAG_Byte_Array was prefixed with a negative length");
                }

                byte[] bytes = new byte[length];
                in.readFully(bytes);

                yield new ByteArrayTag(bytes);
            }
            case LONG_ARRAY -> {
                int length = in.readInt();
                if (length < 0) {
                    throw new NBTException("TAG_Long_Array was prefixed with a negative length");
                } else if (length == 0) {
                    yield new LongArrayTag(0);
                }

                byte[] bytes = new byte[length * 8];
                in.readFully(bytes);
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
                yield new LongArrayTag(longArray);
            }
            case LIST -> readList(in);

            default ->  throw new NBTException("Unknown type: " + type);
        };
    }

    private static ListTag readList(DataInput in) throws IOException {
        ListTag list = new ListTag();
        TagType type0 = readType(in);
        if (type0 == TagType.END) {
            return new ListTag();
        }

        int length = in.readInt();
        for (int i = 0; i < length; i++) {
            list.add(readValue(in, type0));
        }

        return list;
    }

    public static ITag snbtType(String value) {
        try {
            if (FLOAT_PATTERN.matcher(value).matches()) {
                return new FloatTag(Float.parseFloat(value.substring(0, value.length() - 1)));
            }

            if (BYTE_PATTERN.matcher(value).matches()) {
                return new ByteTag(Byte.parseByte(value.substring(0, value.length() - 1)));
            }

            if (LONG_PATTERN.matcher(value).matches()) {
                return new LongTag(Long.parseLong(value.substring(0, value.length() - 1)));
            }

            if (SHORT_PATTERN.matcher(value).matches()) {
                return new ShortTag(Short.parseShort(value.substring(0, value.length() - 1)));
            }

            if (INT_PATTERN.matcher(value).matches()) {
                return new IntTag(Integer.parseInt(value));
            }

            if (DOUBLE_PATTERN.matcher(value).matches()) {
                return new DoubleTag(Double.parseDouble(value.substring(0, value.length() - 1)));
            }

            if (DOUBLE_PATTERN_NOSUFFIX.matcher(value).matches()) {
                return new DoubleTag(Double.parseDouble(value));
            }

            if ("true".equalsIgnoreCase(value)) {
                return new ByteTag((byte) 1);
            }

            if ("false".equalsIgnoreCase(value)) {
                return new ByteTag((byte) 0);
            }
        } catch (NumberFormatException numberformatexception) {
            throw new NBTException(numberformatexception);
        }

        return new StringTag(value);
    }
}
