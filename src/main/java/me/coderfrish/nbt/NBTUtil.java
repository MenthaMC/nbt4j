package me.coderfrish.nbt;

import me.coderfrish.nbt.exceptions.NBTException;
import me.coderfrish.nbt.primitive.*;

import java.io.DataInput;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class NBTUtil {
    public static ITagBase readValue(DataInput inp, ITagBase.TagType type, boolean isList) {
        try {
            String key = !isList ? inp.readUTF() : "";
            return switch (type) {
                case BYTE -> new ByteTag(key, inp.readByte());
                case SHORT -> new ShortTag(key, inp.readShort());
                case INT -> new IntegerTag(key, inp.readInt());
                case LONG -> new LongTag(key, inp.readLong());
                case FLOAT -> new FloatTag(key, inp.readFloat());
                case DOUBLE -> new DoubleTag(key, inp.readDouble());
                case BYTE_ARRAY -> {
                    int length = inp.readInt();
                    if (length < 0) {
                        throw new NBTException("TAG_Byte_Array was prefixed with a negative length");
                    }

                    byte[] bytes = new byte[length];
                    inp.readFully(bytes);

                    yield new ByteArrayTag(key, bytes);
                }
                case STRING -> new StringTag(key, inp.readUTF());
                case LIST -> {
                    ListTag<ITagBase> list = new ListTag<>(key);
                    ITagBase.TagType type0 = ITagBase.TagType.getTypeById(inp.readByte());
                    if (type0 == ITagBase.TagType.END) {
                        yield new ListTag<>(key, new ArrayList<>());
                    }

                    int length = inp.readInt();
                    for (int i = 0; i < length; i++) {
                        list.addItem(readValue(inp, type0, true));
                    }

                    yield list;
                }
                case COMPOUND -> {
                    CompoundTag tag = new CompoundTag(key);
                    for (;;) {
                        ITagBase.TagType type0 = ITagBase.TagType.getTypeById(inp.readByte());
                        if (type0 == ITagBase.TagType.END) break;

                        tag.put(readValue(inp, type0, false));
                    }

                    yield tag;
                }
                case INT_ARRAY -> {
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

                    yield new IntegerArrayTag(key, intArray);
                }
                case LONG_ARRAY -> {
                    int length = inp.readInt();
                    if (length < 0) {
                        throw new NBTException("TAG_Long_Array was prefixed with a negative length");
                    } else if (length == 0) {
                        yield new LongArrayTag(key, new long[0]);
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
                    yield new LongArrayTag(key, longArray);
                }

                default -> throw new NBTException("Unexpected value: " + type);
            };
        } catch (Exception e) {
            throw new NBTException(e);
        }
    }

    private static final int insensitive;
    static {
        insensitive = Pattern.CASE_INSENSITIVE;
    }

    private static final Pattern DOUBLE_PATTERN_NOSUFFIX = Pattern.compile("[-+]?(?:[0-9]+[.]|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?", insensitive);
    private static final Pattern DOUBLE_PATTERN = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?d", Pattern.CASE_INSENSITIVE);
    private static final Pattern FLOAT_PATTERN = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?f", Pattern.CASE_INSENSITIVE);
    private static final Pattern BYTE_PATTERN = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)b", Pattern.CASE_INSENSITIVE);
    private static final Pattern LONG_PATTERN = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)l", Pattern.CASE_INSENSITIVE);
    private static final Pattern SHORT_PATTERN = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)s", Pattern.CASE_INSENSITIVE);
    private static final Pattern INT_PATTERN = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)");

    public static ITagBase snbtType(String value) {
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
                return new IntegerTag(Integer.parseInt(value));
            }

            if (DOUBLE_PATTERN.matcher(value).matches()) {
                return new DoubleTag(Double.parseDouble(value.substring(0, value.length() - 1)));
            }

            if (DOUBLE_PATTERN_NOSUFFIX.matcher(value).matches()) {
                return new DoubleTag(Double.parseDouble(value));
            }

            if ("true".equalsIgnoreCase(value)) {
                return new BooleanTag(true);
            }

            if ("false".equalsIgnoreCase(value)) {
                return new BooleanTag(false);
            }
        } catch (NumberFormatException numberformatexception) {
            throw new NBTException(numberformatexception);
        }

        return new StringTag(value);
    }

    public static int[] intList2Array(List<? extends Integer> list) {
        int[] array = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static long[] longList2Array(List<? extends Long> list) {
        long[] array = new long[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static byte[] byteList2Array(List<? extends Byte> list) {
        byte[] array = new byte[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
