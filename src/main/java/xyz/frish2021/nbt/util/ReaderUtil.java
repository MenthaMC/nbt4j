package xyz.frish2021.nbt.util;

import org.jetbrains.annotations.NotNull;
import xyz.frish2021.nbt.compound.CompoundTag;
import xyz.frish2021.nbt.exception.NBTReaderException;
import xyz.frish2021.nbt.list.ListTag;
import xyz.frish2021.nbt.primitive.StringTag;
import xyz.frish2021.nbt.primitive.number.*;
import xyz.frish2021.nbt.api.ITag;
import xyz.frish2021.nbt.tag.TagType;

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
            case BYTE_ARRAY, LONG_ARRAY, INT_ARRAY, LIST -> readList(in);

            default ->  throw new NBTReaderException("Unknown type: " + type);
        };
    }

    private static @NotNull ListTag<ITag> readList(DataInput in) throws IOException {
        ListTag<ITag> list = new ListTag<>();
        TagType type0 = readType(in);
        if (type0 == TagType.END) {
            return new ListTag<>();
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
            throw new NBTReaderException(numberformatexception);
        }

        return new StringTag(value);
    }
}
